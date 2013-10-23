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

public class JobApplicationInteraction
{
  private JobApplicationManager jobApplicationManager;

  public JobApplicationInteraction(JobApplicationManager jobApplicationManager)
  {
    this.jobApplicationManager = jobApplicationManager;
  }

  public JobApplicationResult apply(Jobseeker jobseeker,
                                    Job job,
                                    Resume resume) 
  {
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = getApplicationResult(application);
    return applicationResult;
  }

  private JobApplicationResult getApplicationResult(UnprocessedApplication application)
  {
    if (application.isValid() &&
        !jobApplicationManager.applicationExistsFor(application.getJobseeker(), application.getJob()))
    {
      SuccessfulApplication success = new SuccessfulApplication(application.getJobseeker(),
                                                                application.getJob(),
                                                                application.getResume());
      jobApplicationManager.add(success);

      return success;
    }    
    return new FailedApplication();
  }
  
  public static boolean requiresProfileCompletion(Jobseeker jobseeker, JobseekerProfile profile)
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
}
