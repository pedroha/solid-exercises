package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;

public class InvalidJobView implements IViewProvider
{
  public Result getViewResult(Map<String, Object> model)
  {
    Result result = new Result("invalidJob", model);
    return result;
  }
}
