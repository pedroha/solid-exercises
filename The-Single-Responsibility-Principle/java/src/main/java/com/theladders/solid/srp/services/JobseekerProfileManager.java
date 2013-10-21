package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
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
    return repository.getProfile(jobseeker.getId());
  }
}
