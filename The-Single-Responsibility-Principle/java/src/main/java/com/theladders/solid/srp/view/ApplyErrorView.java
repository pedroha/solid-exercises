package com.theladders.solid.srp.view;

import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.interfaces.IViewProvider;

public class ApplyErrorView implements IViewProvider
{
  private List<String> errList;

  public ApplyErrorView(List<String> errList) {
    this.errList = errList;
  }

  public Result getViewResult(Map<String, Object> model)
  {
    Result result = new Result("error", model, errList);
    return result;
  }
}
