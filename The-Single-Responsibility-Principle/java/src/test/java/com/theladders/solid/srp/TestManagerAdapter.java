package com.theladders.solid.srp;

import com.theladders.solid.srp.business.helpers.ManagerActions;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;

public class TestManagerAdapter implements ManagerActions
{
  public Resume saveResume(Jobseeker jobseeker,
                           String resumeFileName)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    // TODO Auto-generated method stub
    
  }

  public Resume getActiveResume(Jobseeker jobseeker)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker)
  {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    // TODO Auto-generated method stub
    return false;
  }

  public void add(SuccessfulApplication application)
  {
    // TODO Auto-generated method stub
    
  }
}
