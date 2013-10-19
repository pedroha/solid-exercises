package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.interfaces.IViewProvider;
import com.theladders.solid.srp.job.Job;

public abstract class AbtractSuccessView implements IViewProvider
{
  private Job job;
  
  public AbtractSuccessView(Job job)
  {
    this.job = job;
  }
  
  protected Map<String, Object> getModelWithJob() {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());

    return model;
  }
}
