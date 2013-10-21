package com.theladders.solid.srp.model.job.application;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;

public class UnprocessedApplication
{
  private final Jobseeker jobseeker;
  private final Job job;
  private final Resume resume;

  public UnprocessedApplication(Jobseeker jobseeker,
                                Job job,
                                Resume resume)
  {
    this.jobseeker = jobseeker;
    this.job = job;
    this.resume = resume;
  }

  public boolean isValid()
  {
    return jobseeker != null &&
           job       != null &&
           resume    != null;
  }

  public Jobseeker getJobseeker()
  {
    return jobseeker;
  }

  public Job getJob()
  {
    return job;
  }

  public Resume getResume()
  {
    return resume;
  }
}
