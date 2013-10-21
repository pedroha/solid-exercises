package com.theladders.solid.srp.model;

public class JobseekerProfile
{
  private final int id;
  private final ProfileStatus status;

  public JobseekerProfile(int id, ProfileStatus status)
  {
    this.id = id;
    this.status = status;
  }

  public ProfileStatus getStatus()
  {
    return status;
  }

  public int getId()
  {
    return id;
  }
}
