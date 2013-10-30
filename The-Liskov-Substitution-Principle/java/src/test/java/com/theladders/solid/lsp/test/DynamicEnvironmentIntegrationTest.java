package com.theladders.solid.lsp.test;

import org.junit.*;

import com.theladders.solid.lsp.refactored.*;

import junit.framework.TestCase;


public class DynamicEnvironmentIntegrationTest extends TestCase
{

  private static final String hostName = "www.theladders.com/";

  @Before
  public void setup()
  {   
  }

  public void testSecuredLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = true;
    boolean loggedInUser = true;
    
    Environment env = filter.getEnvironment(isSecure, loggedInUser);

    assertEquals(env.get("staticBase"), null);
    assertEquals(env.get("flash"), null);
    assertEquals(env.get("widgets"), null);
    assertEquals(env.get("home"), "http://www.theladders.com/member/");
    assertEquals(env.get("js"), null);
    assertEquals(env.get("opalImages"), null);
    assertEquals(env.get("autoProtoHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("memberHome"), null);
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("isSSL"), "true");
    assertEquals(env.get("seoImages"), null);
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("landingCss"), null);
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("landingImages"), null);
    assertEquals(env.get("opalCss"), null);
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("images"), null);
    assertEquals(env.get("seoCss"), null);
    assertEquals(env.get("guestHome"), null);
    assertEquals(env.get("landingFlash"), null);
    assertEquals(env.get("css"), null);
    
    // Check that we are accounting for all elements as in the original App
    
    assertEquals(env.keySet().size(), 25);
    assertEquals(env.entrySet().size(), 11);
    assertEquals(env.values().size(), 3);
  }
  
  public void testSecuredNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = true;
    boolean loggedInUser = false;
    
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    
    assertEquals(env.get("staticBase"), null);
    assertEquals(env.get("flash"), null);
    assertEquals(env.get("widgets"), null);
    assertEquals(env.get("home"), "http://www.theladders.com/");
    assertEquals(env.get("js"), null);
    assertEquals(env.get("opalImages"), null);
    assertEquals(env.get("autoProtoHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("memberHome"), null);
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("isSSL"), "true");
    assertEquals(env.get("seoImages"), null);
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("landingCss"), null);
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("landingImages"), null);
    assertEquals(env.get("secureHome"), "https://www.theladders.com/");
    assertEquals(env.get("opalCss"), null);
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("images"), null);
    assertEquals(env.get("seoCss"), null);
    assertEquals(env.get("guestHome"), null);
    assertEquals(env.get("landingFlash"), null);
    assertEquals(env.get("css"), null);

    // Check that we are accounting for all elements as in the original App
    
    assertEquals(env.keySet().size(), 25);
    assertEquals(env.entrySet().size(), 9);
    assertEquals(env.values().size(), 3);
  }

  public void testUnsecuredNotLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = false;
    boolean loggedInUser = false;
    
    Environment env = filter.getEnvironment(isSecure, loggedInUser);
    
    assertEquals(env.get("secureHome"), "https://www.theladders.com/");
    assertEquals(env.get("home"), "http://www.theladders.com/");
    assertEquals(env.get("memberHome"), null);
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("isSSL"), "true");
    assertEquals(env.get("autoProtoHome"), "http://www.theladders.com/");
    assertEquals(env.get("guestHome"), null);
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");

    // Check that we are accounting for all elements as in the original App

    assertEquals(env.keySet().size(), 12);
    assertEquals(env.entrySet().size(), 9);
    assertEquals(env.values().size(), 3);
  }

  public void testUnsecuredLoggedIn()
  {
    EnvSetupFilter filter = new EnvSetupFilter(hostName);

    boolean isSecure = false;
    boolean loggedInUser = true;
    
    Environment env = filter.getEnvironment(isSecure, loggedInUser);

    assertEquals(env.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("memberHome"), null);
    assertEquals(env.get("home"), "http://www.theladders.com/member/");
    assertEquals(env.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(env.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(env.get("isSSL"), "true");
    assertEquals(env.get("autoProtoHome"), "http://www.theladders.com/member/");
    assertEquals(env.get("guestHome"), null);
    assertEquals(env.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(env.get("secureGuestSiteHome"), "https://www.theladders.com/");

    // Check that we are accounting for all elements as in the original App

    assertEquals(env.keySet().size(), 12);
    assertEquals(env.entrySet().size(), 11);
    assertEquals(env.values().size(), 3);
  }
}
