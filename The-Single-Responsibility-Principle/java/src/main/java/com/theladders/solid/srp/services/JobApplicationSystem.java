package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.job.application.FailedApplication;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.model.job.application.UnprocessedApplication;
import com.theladders.solid.srp.persistence.JobApplicationRepository;


public class JobApplicationSystem
{
  private final JobApplicationRepository repository;

  public JobApplicationSystem(JobApplicationRepository repository)
  {
    this.repository = repository;
  }

  public JobApplicationResult apply(UnprocessedApplication application)
  {
    if (application.isValid() &&
        !repository.applicationExistsFor(application.getJobseeker(), application.getJob()))
    {

      SuccessfulApplication success = new SuccessfulApplication(application.getJobseeker(),
                                                                application.getJob(),
                                                                application.getResume());

      repository.add(success);

      return success;
    }

    return new FailedApplication();
  }
}
