package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.job.Job;

public abstract class AbtractViewWithJob extends AbstractView
{
  public AbtractViewWithJob(Job job)
  {
    Map<String, Object> model = getModel();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());
  }
}
