package com.theladders.solid.isp.newjob.segregation;

import java.util.Date;

import com.theladders.solid.isp.newjob.JobEntry;

public class Entry implements JobEntry
{
  private int           subscriberId;
  private int           jobSiteId;
  private String        url;
  private Date          entryDate;
  
  public Entry(int subscriberId,
               int jobSiteId,
               String url,
               Date entryDate)
  {
    this.subscriberId = subscriberId;
    this.jobSiteId = jobSiteId;
    this.url = url;
    this.entryDate = entryDate;
  }

  public Date getEntryDate()
  {
    return entryDate;
  }

  public String getUrl()
  {
    return url;
  }

  public int getSubscriberId()
  {
    return subscriberId;
  }

  public int getJobSiteId()
  {
    return jobSiteId;
  }
}
