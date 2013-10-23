package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.ViewProvider;

public abstract class View implements ViewProvider
{  
  private Map<String, Object> model = new HashMap<>();
  
  public void putData(String key, Object value)
  {
    model.put(key, value);
  }
  
  protected Map<String, Object>getModel()
  {
    return model;    
  }
  
  public void setJob(Job job)
  {
  }
}
