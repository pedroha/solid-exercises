package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.job.Job;

public abstract class AbtractSuccessView extends AbstractView
{
  public AbtractSuccessView(Job job)
  {
    Map<String, Object> model = getModel();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());
  }
}
