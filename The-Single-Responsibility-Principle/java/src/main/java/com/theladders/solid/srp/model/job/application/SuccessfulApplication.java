package com.theladders.solid.srp.model.job.application;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;

public class SuccessfulApplication implements JobApplicationResult
{
  private final Jobseeker jobseeker;
  private final Job job;
  private final Resume resume;

  public SuccessfulApplication(Jobseeker jobseeker,
                               Job job,
                               Resume resume)
  {
    this.jobseeker = jobseeker;
    this.job = job;
    this.resume = resume;
  }

  @Override
  public boolean failure()
  {
    return false;
  }

  public Object getJobseeker()
  {
    return jobseeker;
  }

  public Object getJob()
  {
    return job;
  }

  public Resume getResume()
  {
    return resume;
  }
}
