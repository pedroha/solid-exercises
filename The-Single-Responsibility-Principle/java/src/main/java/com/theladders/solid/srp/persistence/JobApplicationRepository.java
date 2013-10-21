package com.theladders.solid.srp.persistence;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;

public class JobApplicationRepository
{
  private final List<SuccessfulApplication> applications;

  public JobApplicationRepository()
  {
    this.applications = new ArrayList<>();
  }

  public void add(SuccessfulApplication application)
  {
    applications.add(application);
  }

  public boolean applicationExistsFor(Jobseeker jobseeker, Job job)
  {
    for(SuccessfulApplication application : applications)
    {
      if (application.getJobseeker().equals(jobseeker) &&
          application.getJob().equals(job))
      {
        return true;
      }
    }
    return false;
  }
}
