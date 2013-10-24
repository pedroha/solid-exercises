package com.theladders.solid.srp.web;

import com.theladders.solid.srp.util.JobApplicationStatus;
import com.theladders.solid.srp.util.ResponseModel;

public class JobResponseModel implements ResponseModel
{
  private JobApplicationStatus applicationStatus;
  private Object data;
  private String key;
  
  public void setResult(JobApplicationStatus applicationStatus,
                        String key,
                        Object data)
  {
    this.applicationStatus = applicationStatus;
    this.key = key;
    this.data = data;
  }
  
  public void setResult(JobApplicationStatus applicationStatus)
  {
    this.applicationStatus = applicationStatus;
  }

  public JobApplicationStatus getApplicationStatus()
  {
    return applicationStatus;
  }

  public Object getData()
  {
    return data;
  }
  
  public String getKey()
  {
    return key;
  }
}
