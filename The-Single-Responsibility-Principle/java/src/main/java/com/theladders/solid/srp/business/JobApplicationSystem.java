package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.ProfileStatus;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.FailedApplication;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.model.job.application.UnprocessedApplication;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.util.SessionData;

public class JobApplicationSystem
{
  private Managers managers;

  public JobApplicationSystem(Managers managers)
  {
    this.managers = managers;
  }

  public JobApplicationResult processJobApplication(String origFileName,
                                                     Jobseeker jobseeker,
                                                     Job job,
                                                     SessionData sessionData) 
  {
    ResumeSystem resumeSystem = new ResumeSystem(managers.getResumeManager(), managers.getMyResumeManager());
    Resume resume = resumeSystem.retrieveExistingResume(jobseeker, sessionData);
    if (resume == null) {
      resume = resumeSystem.saveNewResume(origFileName,jobseeker, sessionData);
    }
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = getApplicationResult(application);
    return applicationResult;
  }

  private JobApplicationResult getApplicationResult(UnprocessedApplication application)
  {
    if (application.isValid() &&
        !getJobApplicationManager().applicationExistsFor(application.getJobseeker(), application.getJob()))
    {
      SuccessfulApplication success = new SuccessfulApplication(application.getJobseeker(),
                                                                application.getJob(),
                                                                application.getResume());
      getJobApplicationManager().add(success);

      return success;
    }    
    return new FailedApplication();
  }
  
  public static boolean isApplicationComingOutsideTheLadders(Jobseeker jobseeker, JobseekerProfile profile)
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

  private JobApplicationManager getJobApplicationManager()
  {
    return managers.getJobApplicationManager();
  }
}
