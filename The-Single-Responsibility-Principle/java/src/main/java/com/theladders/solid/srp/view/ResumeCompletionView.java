package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.job.Job;

public class ResumeCompletionView extends AbtractSuccessView
{
  public ResumeCompletionView(Job job)
  {
    super(job);
  }
  
  public Result getViewResult()
  {
    Map<String, Object> model = getModelWithJob();
    Result result = new Result("completeResumePlease", model);
    return result;
  }
}
