package com.theladders.solid.lsp.string;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface EnvironmentStore
{
  String get(String name);
  void put(String key, String value);
  
  // May want to add iterator APIs?
  Collection<String>values();
  Set<Map.Entry<String, String>> entrySet();
}
