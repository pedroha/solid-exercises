package com.theladders.solid.isp.newjob;

public interface HarvestedJob
{
  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   *
   * @return URL for this job.
   */
  String getUrl();
}
