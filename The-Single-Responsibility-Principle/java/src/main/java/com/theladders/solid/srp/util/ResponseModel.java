package com.theladders.solid.srp.util;

public interface ResponseModel
{
  void setResult(JobApplicationStatus applicationStatus, Object data);

  JobApplicationStatus getApplicationStatus();
  Object getData();
}
