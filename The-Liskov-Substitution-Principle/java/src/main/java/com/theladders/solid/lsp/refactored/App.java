package com.theladders.solid.lsp.refactored;

import java.util.Scanner;
import java.util.Set;

public class App
{
  private static final String hostName = "www.theladders.com/";

  public static void main(String[] args)
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = ask("Is the HTTP request secure?");
    boolean loggedInUser = ask("Is a user logged into the site?");

    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    
    Set<Object> keys = env.keySet();
    
    for (Object key: keys)
    {
      String value = (env.get(key) != null) ? "\"" + env.get(key) + "\"" : null;
      System.out.println("assertEquals( env.get(\"" + key + "\"), " + value + ");");
    }
    // System.out.println(env);
  }

  @SuppressWarnings("resource")
  public static boolean ask(String question)
  {
    System.out.println(question + " (true/false)");

    return new Scanner(System.in).nextBoolean();
  }
}
