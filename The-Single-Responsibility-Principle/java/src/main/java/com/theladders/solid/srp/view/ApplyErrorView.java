package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;

public class ApplyErrorView implements IViewProvider
{
  private List<String> errList = new ArrayList<>();    
  
  public ApplyErrorView() 
  {
  }
  
  public void addMessage(String errorMessage) 
  {
    errList.add(errorMessage);
  }

  public Result getViewResult()
  {
    Map<String, Object> model = new HashMap<>();
    Result result = new Result("error", model, errList);
    return result;
  }
}
