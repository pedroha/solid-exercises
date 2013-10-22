package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.util.ViewProvider;

public abstract class View implements ViewProvider
{  
  private Map<String, Object> model = new HashMap<>();
  
  protected Map<String, Object> getModel() {
    return model;
  }
}
