package com.theladders.solid.srp.model.job.application;

public class FailedApplication implements JobApplicationResult
{
  @Override
  public boolean failure()
  {
    return true;
  }
}
