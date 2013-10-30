package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.util.Result;

public class ResumeCompletionView extends ViewWithJob
{
  public ResumeCompletionView(Map<String, Object>data)
  {
    super(data);
  }

  public Result getViewResult()
  {
    return new Result("completeResumePlease", getModel());
  }
}
