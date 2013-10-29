package com.theladders.solid.lsp;

import java.util.Scanner;

public class App
{
  private static final String hostName = "www.theladders.com/";

  public static void main(String[] args)
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = ask("Is the HTTP request secure?");
    boolean loggedInUser = ask("Is a user logged into the site?");

    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    
    System.out.println("KeySet: "   + env.keySet().size());
    System.out.println("EntrySet: " + env.entrySet().size());
    System.out.println("Values: "   + env.values().size());

    System.out.println(env);
  }

  @SuppressWarnings("resource")
  public static boolean ask(String question)
  {
    System.out.println(question + " (true/false)");

    return new Scanner(System.in).nextBoolean();
  }
}
