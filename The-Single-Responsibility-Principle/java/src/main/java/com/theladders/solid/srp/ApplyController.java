package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.interfaces.IViewProvider;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.*;


public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  private static boolean isApplicationComingOutsideTheLadders(Jobseeker jobseeker, JobseekerProfile profile) {
    // TODO: Should come from the ApplicationLogic (not from this controller!!)
    boolean isOutside = (!jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
        profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
        profile.getStatus().equals(ProfileStatus.REMOVED)));
    
    return isOutside;
  }
  
  /*
   * Do Application Logic -> Return some state to identify which provider
   * 
   * GetNextViewProvider()
   * 
   */
  
  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    Jobseeker jobseeker = getJobSeeker(request);
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = getJobId(request);
    Job job = jobSearchService.getJob(jobId);

    ApplyErrorView errorView = new ApplyErrorView();
    IViewProvider viewProvider = errorView;
    try
    {
      if (job == null)
      {
        viewProvider = new InvalidJobView(jobId);
      }
      else
      {
        JobApplicationResult applicationResult = processJobApplication(origFileName, 
                                                                       jobseeker, 
                                                                       job,
                                                                       request);
        if (applicationResult.success())
        {
          if (isApplicationComingOutsideTheLadders(jobseeker, profile))
          {
            viewProvider = new ResumeCompletionView(job);
          }
          else {
            viewProvider = new ApplySuccessView(job);
          }        
        }
        else {
          String message = "We could not process your application.";
          errorView.addMessage(message);
          throw new ApplicationFailureException(applicationResult.toString());
        }        
      }
    }
    catch (Exception e)
    {
      errorView.addMessage(e.getMessage()); // This catches error for Missing resume and adds to the list
      viewProvider = errorView;
    }
    Result result = viewProvider.getViewResult();
    response.setResult(result);    
    return response;
  }

  private int getJobId(HttpRequest request)
  {
    String jobIdString = request.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);
    return jobId;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return profile;
  }

  private Jobseeker getJobSeeker(HttpRequest request)
  {
    Jobseeker jobseeker = request.getSession().getJobseeker();
    return jobseeker;
  }
  
  private JobApplicationResult processJobApplication(String origFileName,
                                                     Jobseeker jobseeker,
                                                     Job job,
                                                     HttpRequest request) 
  {
    Resume resume = saveNewOrRetrieveExistingResume(origFileName,jobseeker, request);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);
    return applicationResult;
  }
  
  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 HttpRequest request)
  {
    Resume resume;

    if (!"existing".equals(request.getParameter("whichResume")))
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      if (resume != null && "yes".equals(request.getParameter("makeResumeActive")))
      {
        myResumeManager.saveAsActive(jobseeker, resume);
      }
    }
    else
    {
      resume = myResumeManager.getActiveResume(jobseeker.getId());
    }

    return resume;
  }
}
