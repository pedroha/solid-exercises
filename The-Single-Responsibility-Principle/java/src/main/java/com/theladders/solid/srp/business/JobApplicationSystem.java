package com.theladders.solid.srp.business;

import com.theladders.solid.srp.Managers;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.ProfileStatus;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.ApplicationFailureException;
import com.theladders.solid.srp.model.job.application.FailedApplication;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.model.job.application.UnprocessedApplication;
import com.theladders.solid.srp.services.JobApplicationService;
import com.theladders.solid.srp.util.IViewProvider;
import com.theladders.solid.srp.util.SessionData;
import com.theladders.solid.srp.view.*;


public class JobApplicationSystem
{
  private JobApplicationService jobApplicationService = null;
  private Managers managers;

  public JobApplicationSystem(Managers managers)
  {
    this.managers = managers;
    this.jobApplicationService = managers.getJobApplicationService();
  }
  
  public IViewProvider getViewProvider(SessionData sessionData,
                              String origFileName)
  {
    Jobseeker jobseeker = sessionData.getJobseeker();
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = sessionData.getJobId();
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

  private JobApplicationResult processJobApplication(String origFileName,
                                                     Jobseeker jobseeker,
                                                     Job job,
                                                     SessionData sessionData) 
  {
    ResumeHandler resumeBusiness = new ResumeHandler(managers);
    Resume resume = resumeBusiness.saveNewOrRetrieveExistingResume(origFileName,jobseeker, sessionData);  
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = applyForJob(application);
    return applicationResult;
  }

  private JobApplicationResult applyForJob(UnprocessedApplication application)
  {
    if (application.isValid() &&
        !jobApplicationService.applicationExistsFor(application.getJobseeker(), application.getJob()))
    {
      SuccessfulApplication success = new SuccessfulApplication(application.getJobseeker(),
                                                                application.getJob(),
                                                                application.getResume());
      jobApplicationService.add(success);

      return success;
    }
    
    return new FailedApplication();
  }
  
  private static boolean isApplicationComingOutsideTheLadders(Jobseeker jobseeker, JobseekerProfile profile)
  {
    ProfileStatus status = profile.getStatus();
    boolean isOutside = 
      (!jobseeker.isPremium() &&
        (
          status.equals(ProfileStatus.INCOMPLETE) ||
          status.equals(ProfileStatus.NO_PROFILE) ||
          status.equals(ProfileStatus.REMOVED)
        )
      );    
    return isOutside;
  }
  
  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = managers.getJobseekerProfileManager().getJobSeekerProfile(jobseeker);
    return profile;
  }  
}
