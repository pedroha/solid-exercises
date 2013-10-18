package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;
import com.theladders.solid.srp.job.Job;

public class ResumeCompletionView implements IViewProvider
{
  private Job job;
  
  public ResumeCompletionView(Job job)
  {
    this.job = job;
  }
  
  public Result getViewResult()
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());

    Result result = new Result("completeResumePlease", model);
    return result;
  }
}
