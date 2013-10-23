package com.theladders.solid.srp.util;

import com.theladders.solid.srp.model.Jobseeker;

public interface RequestModel
{
  Jobseeker getJobseeker();
  int getJobId();
  ResumeFile getResumeFile();
  boolean hasExistingResume();
  boolean makeResumeActive();
}
