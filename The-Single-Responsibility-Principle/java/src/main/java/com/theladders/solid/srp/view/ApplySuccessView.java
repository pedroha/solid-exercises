package com.theladders.solid.srp.view;

import com.theladders.solid.srp.util.Result;

public class ApplySuccessView extends ViewWithJob
{
  public Result getViewResult()
  {
    return new Result("success", getModel());
  }
}
