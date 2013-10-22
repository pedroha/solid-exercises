package com.theladders.solid.srp.util;

import com.theladders.solid.srp.model.Jobseeker;

public interface RequestModel
{
  boolean hasExistingResume();
  boolean makeResumeActive();
  Jobseeker getJobseeker();
  int getJobId();
}
