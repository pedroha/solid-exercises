package com.theladders.solid.lsp.string;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Environment implements EnvironmentStore
{
  public static final String KEY_EMAIL_DOMAIN = "emaildomain";

  public Environment()
  {
    this.mapping = new HashMap<>();
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

  public String get(String name)
  {
    return mapping.get(name);
  }

  public void put(String key, String value)
  {
    mapping.put(key, value);
  }
  
  public Collection<String>values()
  {
    return mapping.values();
  }
  
  public Set<Map.Entry<String, String>> entrySet()
  {
    return mapping.entrySet();
  }

  public Set<String> keySet()
  {
    return mapping.keySet();
  }

  private Map<String, String> mapping;
}
