package com.theladders.solid.srp.util;

import java.util.List;
import java.util.Map;

public class Result
{
  private final String type;
  private final Map<String, Object> model;
  private final List<String> errorList;

  public Result(String type,
                Map<String, Object> model)
  {
    this.type  = type;
    this.model = model;
    this.errorList = null;
  }

  public Result(String type,
                Map<String, Object> model,
                List<String> errList)
  {
    this.type  = type;
    this.model = model;
    this.errorList = errList;
  }

  public String getType()
  {
    return type;
  }
  
  public Object get(String name)
  {
    return model.get(name);
  }
  
  public String getError()
  {
    return (errorList.size() > 0 ? errorList.get(0) : "");
  }

  @Override
  public String toString()
  {
    return "Result [type=" + type + ", model=" + model + ", errorList=" + errorList + "]";
  }
}
