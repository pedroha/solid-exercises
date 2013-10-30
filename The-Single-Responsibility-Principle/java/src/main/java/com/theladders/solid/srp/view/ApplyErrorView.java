package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.util.Result;

public class ApplyErrorView extends View
{
  private List<String> errList = new ArrayList<>();    
 
  public void addMessage(String errorMessage) 
  {
    errList.add(errorMessage);
  }

  public Result getViewResult()
  {
    return new Result("error", getModel(), errList);
  }
}
