package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;

public class InvalidJobView extends AbstractView
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

