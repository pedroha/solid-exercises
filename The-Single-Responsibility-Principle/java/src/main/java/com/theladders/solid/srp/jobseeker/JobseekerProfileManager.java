package com.theladders.solid.srp.jobseeker;

import com.theladders.solid.srp.persistence.JobseekerProfileRepository;


public class JobseekerProfileManager
{
  private final JobseekerProfileRepository repository;

  public JobseekerProfileManager(JobseekerProfileRepository repository)
  {
    this.repository = repository;
  }

  public JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker)
  {
    return  repository.getProfile(jobseeker.getId());
  }
}
