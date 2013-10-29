package com.theladders.solid.lsp.refactored;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A wrapper that allows some properties to be overriden on a per-request basis.
 *
 * @author Zhi-Da Zhong &lt;zz@theladders.com&gt;
 */

public class DynamicEnvironment implements Environment
{
  private BaseEnvironment       base;
  private Map<String, String>   keyMap; // map insecure prop names to secure ones
  private Map<Object, Object>   dynamicMap;

  public DynamicEnvironment(BaseEnvironment base, Map<String, String> propKeyMap)
  {
    this.base = base;
    this.keyMap = propKeyMap;
    this.dynamicMap = new HashMap<>();
  }

  public Collection<Object> values()
  {
    // TODO remove masked values
    // TODO join local instance values
    return base.values();
  }

  /**
   * This method uses a mapped version of the given key to access first its own Map then its
   * underlying Map.
   *
   * @param key
   *          An environment key like "home"
   * @return The value for the given key after mapping (e.g. "home" might be mapped to "secureHome")
   */

  public Object get(Object key)
  {
    String realKey = keyMap.get(key);
    Object value = dynamicMap.get(realKey != null ? realKey : key);
    if (value == null)
    {
      return base.get(realKey != null ? realKey : key);
    }
    return value;
  }

  public Set<Map.Entry<Object, Object>> entrySet()
  {
    Set<Map.Entry<Object, Object>> entrySet = new HashSet<>(dynamicMap.entrySet());
    entrySet.addAll(base.entrySet());
    return Collections.unmodifiableSet(entrySet);
  }

  public Set<Object> keySet()
  {
    Set<Object> keySet = new HashSet<>(dynamicMap.keySet());
    keySet.addAll(keyMap.keySet());
    keySet.addAll(base.keySet());
    return Collections.unmodifiableSet(keySet);
  }

  public void put(Object key, Object value)
  {
    dynamicMap.put(key, value);
  }
  
  public String getString(String key)
  {
    Object val = get(key);
    return (val != null) ? val.toString().trim() : "";
  }
  
  @Override
  public String toString()
  {
    return dynamicMap.toString();
  }
}