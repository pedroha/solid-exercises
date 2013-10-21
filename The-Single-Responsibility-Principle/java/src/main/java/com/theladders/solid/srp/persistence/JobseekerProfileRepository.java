package com.theladders.solid.srp.persistence;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.model.JobseekerProfile;

public class JobseekerProfileRepository
{
  private Map<Integer, JobseekerProfile> profiles;

  public JobseekerProfileRepository()
  {
    this.profiles = new HashMap<>();
  }

  public JobseekerProfile getProfile(int jobseekerId)
  {
    return profiles.get(jobseekerId);
  }

  public void addProfile(JobseekerProfile profile)
  {
    profiles.put(profile.getId(), profile);
  }
}
