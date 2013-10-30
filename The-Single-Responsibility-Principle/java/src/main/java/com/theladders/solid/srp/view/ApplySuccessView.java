package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.util.Result;

public class ApplySuccessView extends ViewWithJob
{
  public ApplySuccessView(Map<String, Object>data)
  {
    super(data);
  }

  public Result getViewResult()
  {
    return new Result("success", getModel());
  }
}
