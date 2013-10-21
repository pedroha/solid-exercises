package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.persistence.JobRepository;


public class JobManager
{
  private final JobRepository repository;

  public JobManager(JobRepository repository)
  {
    this.repository = repository;
  }

  public Job getJob(int jobId)
  {
    return repository.getJob(jobId);
  }
}
