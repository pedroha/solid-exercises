package com.theladders.solid.isp.newjob;

import java.util.Date;

public interface JobInternal
{
  int getOldJobId();

  Date getUpdateTime();

  /**
   * Get the (internally set) editor's note.
   * 
   * @return editor's note.
   */
  String getEditorNote();

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

  /**
   * Is this job a JobReq? JobReqs are jobs entered into our site directly by recruiters.
   * 
   * @return true if job is a JobReq, false otherwise.
   */
  boolean isJobReq();
}