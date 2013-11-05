package com.theladders.solid.isp.newjob.implementation;

import java.util.Date;

import com.theladders.solid.isp.newjob.JobPostStatus;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;

public class PostStatus implements JobPostStatus
{
  private boolean       deleted;
  private boolean       expired;
  private Date          updateTime;
  
  public PostStatus(boolean deleted,
                    boolean expired,
                    Date updateTime)
  {
    this.deleted = deleted;
    this.expired = expired;
    this.updateTime = updateTime;
  }

  public boolean isDeleted()
  {
    return deleted;
  }

  public boolean isExpired()
  {
    return expired;
  }

  public Date getUpdateTime()
  {
    return updateTime;
  }

  public boolean hasStatus(JobStatus status) // TODO: what's the logic and allowable JobStatus?
  {
    return false;
  }
}