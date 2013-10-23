package com.theladders.solid.srp.util;

public interface ResponseModel
{
  void setResult(JobApplicationStatus applicationStatus, String key, Object data);

  JobApplicationStatus getApplicationStatus();
  String getKey();
  Object getData();
}
