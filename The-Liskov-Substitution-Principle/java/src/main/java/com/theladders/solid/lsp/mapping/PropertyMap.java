package com.theladders.solid.lsp.mapping;

import java.util.HashMap;
import java.util.Map;

public class PropertyMap
{
  public PropertyMap()
  {
    this.mapping = new HashMap<>();
  }
  
  public String get(String key)
  {
    return mapping.get(key);
  }
  
  public void put(String key, String mapped)
  {
    mapping.put(key, mapped);
  }

  private Map<String, String>mapping;
}
