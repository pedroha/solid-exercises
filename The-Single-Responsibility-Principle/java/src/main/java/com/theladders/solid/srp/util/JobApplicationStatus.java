package com.theladders.solid.srp.util;

public enum JobApplicationStatus
{
  INVALID_JOB(17, "InvalidJob"),
  NEEDS_PROFILE_COMPLETION(18, "Needs profile completion"),
  COMPLETE(22, "Complete"),
  ERROR(25, "Error");

  private int    id;
  private String name;
  
  JobApplicationStatus(final int id,
                       final String name)
  {
    this.id = id;
    this.name = name;
  }
}

