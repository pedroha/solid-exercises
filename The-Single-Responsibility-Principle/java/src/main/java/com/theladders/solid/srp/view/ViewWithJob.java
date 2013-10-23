package com.theladders.solid.srp.view;

import com.theladders.solid.srp.model.Job;

public abstract class ViewWithJob extends View
{
  public void setJob(Job job)
  {
    putData("jobId", job.getJobId());
    putData("jobTitle", job.getTitle());
  }
}
