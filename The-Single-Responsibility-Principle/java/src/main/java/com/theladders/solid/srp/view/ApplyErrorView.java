package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.util.Result;

public class ApplyErrorView extends AbstractView
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
    Map<String, Object> model = getModel();
    Result result = new Result("error", model, errList);
    return result;
  }
}
