package com.theladders.solid.isp.newjob;

public interface JobIdentifiers
{
  int getOldJobId();

  /**
   * Returns a unique identifier for this job. In the web application, this currently maps to
   * job_location_id in the Database. Scripts may use other values.
   * 
   * @return unique identifier for this job.
   */
  int getJobId();

  /**
   * Returns the real job_id.
   * 
   * @return job id
   */
  Integer getParentJobId();

}