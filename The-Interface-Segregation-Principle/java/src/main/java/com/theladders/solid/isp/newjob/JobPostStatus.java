package com.theladders.solid.isp.newjob;

import java.util.Date;

import com.theladders.solid.isp.oldjob.stubs.JobStatus;

public interface JobPostStatus
{
  boolean isDeleted();

  boolean isExpired();

  /**
   * Does the job have a particular status? There's a legacy thing where a job could have more than
   * one status, hence this method... Status should be moved out of PublicationInfo though, and this
   * should be a getStatus() method...
   *
   * @param status
   *          status to check against.
   * @return true if job has this status, false otherwise.
   */
  boolean hasStatus(JobStatus status);

  Date getUpdateTime();
}
