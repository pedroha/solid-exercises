package com.theladders.solid.srp.util;

import java.util.HashMap;
import java.util.Map;

public class JobApplyResult
{
  private Map<String, Object>   resultData;
  private JobApplicationStatus  status;
  
  public JobApplyResult(JobApplicationStatus status)
  {
    this.resultData = new HashMap<>();
    this.status = status;
  }
  
  public JobApplicationStatus getStatus()
  {
    return status;
  }
  
  public void set(String key, Object value)
  {
    resultData.put(key, value);
  }
  
  public Map<String, Object>getData()
  {
    return resultData;
  }
}
