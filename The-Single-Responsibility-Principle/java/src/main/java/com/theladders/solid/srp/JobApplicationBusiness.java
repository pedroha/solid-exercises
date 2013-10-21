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


public class JobApplicationBusiness
{
  private Managers managers;

  public JobApplicationBusiness(Managers managers)
  {
    this.managers = managers;
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
    ResumeBusiness resumeBusiness = new ResumeBusiness(managers);
    Resume resume = resumeBusiness.saveNewOrRetrieveExistingResume(origFileName,jobseeker, sessionData);  
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = managers.getJobApplicationSystem().apply(application);
    return applicationResult;
  }

  private static boolean isApplicationComingOutsideTheLadders(Jobseeker jobseeker, JobseekerProfile profile)
  {
    boolean isOutside = (!jobseeker.isPremium() && (
        profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
        profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
        profile.getStatus().equals(ProfileStatus.REMOVED)));
    
    return isOutside;
  }
  
  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = managers.getJobseekerProfileManager().getJobSeekerProfile(jobseeker);
    return profile;
  }  
}
