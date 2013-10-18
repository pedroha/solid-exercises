package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;

public class InvalidJobView implements IViewProvider
{
  private int jobId;

  public InvalidJobView(int jobId) {
    this.jobId = jobId;
  }
  
  public Result getViewResult()
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", jobId);
    
    Result result = new Result("invalidJob", model);
    return result;
  }
}

