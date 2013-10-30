package com.theladders.solid.srp.web;

import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.ResponseModel;

public class JobResponseModel implements ResponseModel
{
  private JobApplyResult result;
  
  public void setResult(JobApplyResult result)
  {
    this.result = result;
  }

  public JobApplyResult getJobApplyResult()
  {
    return result;
  }
}
