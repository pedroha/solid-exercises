package com.theladders.solid.srp.view;

import com.theladders.solid.srp.util.Result;

public class ResumeCompletionView extends ViewWithJob
{
  public Result getViewResult()
  {
    return new Result("completeResumePlease", getModel());
  }
}
