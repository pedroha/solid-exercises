package com.theladders.solid.srp.view;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.job.Job;

public class ApplySuccessView extends AbtractSuccessView
{
  public ApplySuccessView(Job job)
  {
    super(job);
  }

  public Result getViewResult()
  {
    Result result = new Result("success", getModel());
    return result;
  }
}
