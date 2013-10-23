package com.theladders.solid.srp.view;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.Result;

public class ApplySuccessView extends ViewWithJob
{
  public ApplySuccessView(Job job)
  {
    super(job);
  }

  public Result getViewResult()
  {
    return new Result("success", getModel());
  }
}
