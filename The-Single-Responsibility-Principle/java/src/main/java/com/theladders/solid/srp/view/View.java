package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.util.ViewProvider;

public abstract class View implements ViewProvider
{  
  private Map<String, Object> model;
  
  public View(Map<String, Object> model)
  {
    this.model = model;
  }
  
  public void putData(String key, Object value)
  {
    model.put(key, value);
  }
  
  protected Map<String, Object>getModel()
  {
    return model;    
  }
}
