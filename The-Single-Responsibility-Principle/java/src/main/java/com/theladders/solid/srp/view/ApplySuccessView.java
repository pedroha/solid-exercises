package com.theladders.solid.srp.view;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.Result;

public class ApplySuccessView extends AbtractViewWithJob
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
