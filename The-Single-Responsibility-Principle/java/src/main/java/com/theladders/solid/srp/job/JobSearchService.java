package com.theladders.solid.srp.job;

import com.theladders.solid.srp.persistence.JobRepository;


public class JobSearchService
{
  private final JobRepository repository;

  public JobSearchService(JobRepository repository)
  {
    this.repository = repository;
  }

  public Job getJob(int jobId)
  {
    return repository.getJob(jobId);
  }
}
