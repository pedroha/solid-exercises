package com.theladders.solid.isp.newjob.segregation;

import com.theladders.solid.isp.newjob.JobIdentifiers;

public class Identifiers implements JobIdentifiers
{
  private int jobId;
  private int parentJobId;
  private int oldJobId;

  public Identifiers(int jobId,
                     int parentJobId,
                     int oldJobId)
  {
    this.jobId = jobId;
    this.parentJobId = parentJobId;
    this.oldJobId = oldJobId;
  }

  public int getJobId()
  {
    return jobId;
  }

  public Integer getParentJobId()
  {
    return parentJobId;
  }

  public int getOldJobId()
  {
    return oldJobId;
  }
}
