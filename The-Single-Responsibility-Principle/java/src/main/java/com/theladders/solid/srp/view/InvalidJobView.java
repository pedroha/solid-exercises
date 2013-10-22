package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.util.Result;

public class InvalidJobView extends View
{
  public InvalidJobView(int jobId)
  {
    Map<String, Object> model = getModel();
    model.put("jobId", jobId);
  }
  
  public Result getViewResult()
  {
    Result result = new Result("invalidJob", getModel());
    return result;
  }
}

