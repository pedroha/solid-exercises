package com.theladders.solid.lsp.mapping;

public class NoSSLPropertyMap extends PropertyMap implements HomeProperties
{
  public NoSSLPropertyMap()
  {
    this.put("secureHome", "home");
    this.put(AUTO_HOME, "home");

    this.put("secureGuestHome", "insecureGuestHome");
    this.put("secureMemberHome", "insecureMemberHome");
    this.put("secureStaticBase", "staticBase");
    this.put(MEMBER_HOME, "insecureMemberHome");
    this.put(GUEST_HOME, "insecureGuestHome");
  }
}
