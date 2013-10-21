package com.theladders.solid.srp.persistence;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.model.Job;


public class JobRepository
{
  private final Map<Integer, Job> jobs;

  public JobRepository()
  {
    this.jobs = new HashMap<>();
  }

  public void addJob(Job aJob)
  {
    jobs.put(aJob.getJobId(), aJob);
  }

  public Job getJob(int jobId)
  {
    return jobs.get(jobId);
  }
}
