package com.theladders.solid.isp.newjob;

import java.util.Date;

public interface JobEntry
{
  Date getEntryDate();

  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   *
   * @return URL for this job.
   */
  String getUrl();

  int getSubscriberId();

  int getJobSiteId();
}
