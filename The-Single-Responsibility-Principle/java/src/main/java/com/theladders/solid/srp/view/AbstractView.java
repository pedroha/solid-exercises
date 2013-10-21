package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.util.IViewProvider;

public abstract class AbstractView implements IViewProvider
{  
  private Map<String, Object> model = new HashMap<>();
  
  protected Map<String, Object> getModel() {
    return model;
  }
}
