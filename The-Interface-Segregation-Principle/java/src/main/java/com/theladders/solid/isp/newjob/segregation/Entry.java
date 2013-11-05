package com.theladders.solid.isp.newjob.segregation;

import java.util.Date;

import com.theladders.solid.isp.newjob.JobEntry;

public class Entry implements JobEntry
{
  private Date   entryDate;
  private String url;

  public Entry(Date entryDate,
               String url)
  {
    this.entryDate = entryDate;
    this.url = url;
  }

  public Date getEntryDate()
  {
    return entryDate;
  }

  public String getUrl()
  {
    return url;
  }
}
