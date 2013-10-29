package com.theladders.solid.lsp.refactored;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BaseEnvironment implements Environment
{
  public static final String KEY_EMAIL_DOMAIN = "emaildomain";

  public BaseEnvironment()
  {
    this.baseMap = new HashMap<>();
  }

  /**
   * Convenience method that returns the admin email address for this ladder.
   *
   * @return email address or "" if either the user or domain is not defined
   */

  public String getAdminEmail()
  {
    String user = getString("admin");
    String domain = getString(KEY_EMAIL_DOMAIN);

    return user.length() > 0 && domain.length() > 0 ? user + "@" + domain : "";
  }

  public String getString(String key)
  {
    Object val = get(key);
    return (val != null) ? val.toString().trim() : "";
  }

  
  public Object get(Object name)
  {
    return baseMap.get(name);
  }

  public void put(Object key,
                  Object value)
  {
    baseMap.put(key, value);
  }

  public Collection<Object> values()
  {
    return baseMap.values();
  }

  public Set<Entry<Object, Object>> entrySet()
  {
    return baseMap.entrySet();
  }

  public Set<Object> keySet()
  {
    return baseMap.keySet();
  }

  private Map<Object, Object> baseMap;
}
