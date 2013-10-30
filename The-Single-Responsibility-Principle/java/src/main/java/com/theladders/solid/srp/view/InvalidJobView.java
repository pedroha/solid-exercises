package com.theladders.solid.srp.view;

import com.theladders.solid.srp.util.Result;

public class InvalidJobView extends View
{
  public Result getViewResult()
  {
    return new Result("invalidJob", getModel());
  }
}
