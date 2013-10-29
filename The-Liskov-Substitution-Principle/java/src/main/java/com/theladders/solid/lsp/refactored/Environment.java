package com.theladders.solid.lsp.refactored;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Environment
{
  Object get(Object name);
  void put(Object key, Object value);
  
  Collection<Object>values();
  Set<Map.Entry<Object, Object>> entrySet();
  Set<Object> keySet();
}
