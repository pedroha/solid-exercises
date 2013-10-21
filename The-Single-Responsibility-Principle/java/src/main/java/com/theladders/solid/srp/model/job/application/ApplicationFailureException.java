package com.theladders.solid.srp.model.job.application;

public class ApplicationFailureException extends RuntimeException
{
  public ApplicationFailureException(String reason)
  {
    super(reason);
  }
}
