package com.theladders.solid.srp.business;

import com.theladders.solid.srp.business.helpers.ManagerActions;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.ProfileStatus;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.FailedApplication;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.model.job.application.UnprocessedApplication;

public class JobApplicationInteraction
{
  private ManagerActions managerActions;

  public JobApplicationInteraction(ManagerActions managerActions)
  {
    this.managerActions = managerActions;
  }

  public JobApplicationResult apply(Jobseeker jobseeker,
                                    Job job,
                                    Resume resume) 
  {
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    
    if (application.isValid() &&
        !managerActions.applicationExistsFor(jobseeker, job))
    {
      SuccessfulApplication success = new SuccessfulApplication(jobseeker,
                                                                job,
                                                                resume);
      managerActions.add(success);

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
