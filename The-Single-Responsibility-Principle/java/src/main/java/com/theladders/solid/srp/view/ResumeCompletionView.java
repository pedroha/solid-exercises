package com.theladders.solid.srp.view;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.Result;

public class ResumeCompletionView extends AbtractViewWithJob
{
  public ResumeCompletionView(Job job)
  {
    super(job);
  }
  
  public Result getViewResult()
  {
    Result result = new Result("completeResumePlease", getModel());
    return result;
  }
}
