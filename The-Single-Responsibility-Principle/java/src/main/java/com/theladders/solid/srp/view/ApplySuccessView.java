package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;

public class ApplySuccessView implements IViewProvider
{
  public ApplySuccessView() {
  }

  public Result getViewResult(Map<String, Object> model)
  {
    Result result = new Result("success", model);
    return result;
  }
}
