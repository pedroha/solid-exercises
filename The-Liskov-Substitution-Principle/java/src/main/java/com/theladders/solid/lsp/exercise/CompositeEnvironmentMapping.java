package com.theladders.solid.lsp.exercise;

public class CompositeEnvironmentMapping implements Environment
{
  private Environment overrides;
  private Environment base;

  public CompositeEnvironmentMapping(Environment base)
  {
    this.base = base;
    this.overrides = new EnvironmentMapping();
  }
  
  public void set(String name,
                  String value)
  {
    overrides.set(name,  value);
  }

  public String get(String name)
  {
    String value = overrides.get(name);
    if (value == null) // not found
    {
      value = base.get(name);
    }
    return value;
  }
}
