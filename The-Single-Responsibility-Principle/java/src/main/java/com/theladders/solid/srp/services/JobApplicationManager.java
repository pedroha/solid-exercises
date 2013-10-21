package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.persistence.JobApplicationRepository;


public class JobApplicationManager
{
  private final JobApplicationRepository repository;

  public JobApplicationManager(JobApplicationRepository repository)
  {
    this.repository = repository;
  }

  public void add(SuccessfulApplication application)
  {
    repository.add(application);
  }

  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    return repository.applicationExistsFor(jobseeker, job);
  }
}
