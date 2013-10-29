package com.theladders.solid.lsp.exercise;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentMapping implements Environment
{
  private Map<String, String> mappings = new HashMap<>();

  public void set(String name,
                  String value)
  {
    mappings.put(name, value);
  }

  public String get(String name)
  {
    return mappings.get(name);
  }
}
