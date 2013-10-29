package com.theladders.solid.lsp.refactored;

// Just a stub. Nothing to see here.

public class EnvironmentFactory
{
  public static BaseEnvironment getEnvironmentFor(String hostName)
  {
    BaseEnvironment env = new BaseEnvironment();

    env.put("isSSL", "true");
    env.put("home", "http://" + hostName);
    env.put("secureHome", "https://" + hostName);

    return env;
  }
}
