package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.model.Job;

public abstract class ViewWithJob extends View
{
  public ViewWithJob(Map<String, Object>data)
  {
    super(data);
  }
  
  public void setJob(Job job)
  {
    putData("jobId", job.getJobId());
    putData("jobTitle", job.getTitle());
  }
}
