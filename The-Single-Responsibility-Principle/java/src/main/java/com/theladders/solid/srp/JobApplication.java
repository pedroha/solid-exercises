package com.theladders.solid.srp;

import com.theladders.solid.srp.interfaces.IViewProvider;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.view.*;


public class JobApplication
{

  private Managers managers;

  public JobApplication(Managers managers)
  {
    this.managers = managers;
  }
  
  private static boolean isApplicationComingOutsideTheLadders(Jobseeker jobseeker, JobseekerProfile profile) {
    // TODO: Should come from the ApplicationLogic (not from this controller!!)
    boolean isOutside = (!jobseeker.isPremium() && (
        profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
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
  
  public IViewProvider handle(SessionData sessionData,
                              String origFileName)
  {
    Jobseeker jobseeker = getJobSeeker(sessionData);
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = getJobId(sessionData);
    Job job = managers.getJobSearchService().getJob(jobId);

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
                                                                       sessionData);
        if (applicationResult.success())
        {
          if (isApplicationComingOutsideTheLadders(jobseeker, profile))
          {
            viewProvider = new ResumeCompletionView(job);
          }
          else
          {
            viewProvider = new ApplySuccessView(job);
          }        
        }
        else
        {
          errorView.addMessage("We could not process your application.");
          throw new ApplicationFailureException(applicationResult.toString());
        }        
      }
    }
    catch (Exception e)
    {
      errorView.addMessage(e.getMessage()); // This catches error for Missing resume and adds to the list
      viewProvider = errorView;
    }
    return viewProvider;
  }

  private int getJobId(SessionData sessionData)
  {
    String jobIdString = sessionData.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);
    return jobId;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = managers.getJobseekerProfileManager().getJobSeekerProfile(jobseeker);
    return profile;
  }

  private Jobseeker getJobSeeker(SessionData sessionData)
  {
    Jobseeker jobseeker = sessionData.getJobseeker();
    return jobseeker;
  }
  
  private JobApplicationResult processJobApplication(String origFileName,
                                                     Jobseeker jobseeker,
                                                     Job job,
                                                     SessionData sessionData) 
  {
    Resume resume = saveNewOrRetrieveExistingResume(origFileName,jobseeker, sessionData);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = managers.getJobApplicationSystem().apply(application);
    return applicationResult;
  }
  
  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 SessionData sessionData)
  {
    Resume resume;

    if (!"existing".equals(sessionData.getParameter("whichResume")))
    {
      resume = managers.getResumeManager().saveResume(jobseeker, newResumeFileName);

      if (resume != null && "yes".equals(sessionData.getParameter("makeResumeActive")))
      {
        managers.getMyResumeManager().saveAsActive(jobseeker, resume);
      }
    }
    else
    {
      resume = managers.getMyResumeManager().getActiveResume(jobseeker.getId());
    }

    return resume;
  }
}
