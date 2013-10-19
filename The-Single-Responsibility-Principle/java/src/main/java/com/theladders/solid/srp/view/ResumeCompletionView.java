package com.theladders.solid.srp.view;

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
    Result result = new Result("completeResumePlease", getModel());
    return result;
  }
}
