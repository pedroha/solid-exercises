package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.JobApplicationStatus;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;

public class ApplicationStatus implements JobApplicationStatus
{
  public ApplicationStatus(boolean deleted,
                           boolean expired,
                           boolean filled)
  {
    this.deleted = deleted;
    this.expired = expired;
    this.filled = filled;
  }

  public boolean isDeleted()
  {
    return deleted;
  }

  public boolean isExpired()
  {
    return expired;
  }

  public boolean isFilled()
  {
    return filled;
  }

  public boolean hasStatus(JobStatus status) 
  {
    // TODO: not sure how this works?
    return false;
  }

  private boolean deleted;
  private boolean expired;
  private boolean filled;
}
