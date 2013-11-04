package com.theladders.solid.srp.util;

import com.theladders.solid.srp.model.Job;

public interface JobApplicationPresenter
{
  Result error(String errorMessage);
  Result success(Job job);
  Result completeProfile(Job job);
  Result invalidJob(int jobId);
}
