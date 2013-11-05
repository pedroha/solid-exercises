package com.theladders.solid.isp.newjob.implementation;

import java.util.Date;

import com.theladders.solid.isp.newjob.JobIdentifiers;

public class Identifiers implements JobIdentifiers
{
  public Identifiers(int oldJobId,
                  Date updateTime,
                  String editorNote,
                  int jobId,
                  Integer parentJobId,
                  boolean jobReq)
  {
    this.oldJobId = oldJobId;
    this.updateTime = updateTime;
    this.editorNote = editorNote;
    this.jobId = jobId;
    this.parentJobId = parentJobId;
    this.jobReq = jobReq;
  }
  
  public int getOldJobId()
  {
    return oldJobId;
  }

  public Date getUpdateTime()
  {
    return updateTime;
  }

  public String getEditorNote()
  {
    return editorNote;
  }

  public int getJobId()
  {
    return jobId;
  }

  public Integer getParentJobId()
  {
    return parentJobId;
  }

  public boolean isJobReq()
  {
    return jobReq;
  }
  
  private int           oldJobId;
  private Date          updateTime;
  private String        editorNote;
  private int           jobId;
  private Integer       parentJobId;
  private boolean       jobReq;
}
