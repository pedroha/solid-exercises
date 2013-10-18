package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;
import com.theladders.solid.srp.job.Job;

public class ApplySuccessView implements IViewProvider
{
  private Job job;

  public ApplySuccessView(Job job) {
    this.job = job;
  }

  public Result getViewResult()
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());

    Result result = new Result("success", model);
    return result;
  }
}
