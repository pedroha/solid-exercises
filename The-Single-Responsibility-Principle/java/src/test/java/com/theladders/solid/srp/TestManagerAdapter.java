package com.theladders.solid.srp;

import com.theladders.solid.srp.business.helpers.ManagerActions;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.ProfileStatus;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;

public class TestManagerAdapter implements ManagerActions
{
  private Resume        activeResume;
  
  public Resume saveResume(Jobseeker jobseeker,
                           String resumeFileName)
  {
    activeResume = new Resume(resumeFileName);
    return activeResume;
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    activeResume = resume;
  }

  public Resume getActiveResume(Jobseeker jobseeker)
  {
    return activeResume;
  }

  public JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = new JobseekerProfile(jobseeker.getId(), ProfileStatus.NO_PROFILE);
    return profile;
  }

  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    return false;
  }

  public void add(SuccessfulApplication application)
  {
    // Maybe add some extra checks here!
  }
}
