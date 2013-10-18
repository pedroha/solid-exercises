package com.theladders.solid.srp.view;

import java.util.Map;

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
    Map<String, Object> model = getModelWithJob();
    Result result = new Result("success", model);
    return result;
  }
}
