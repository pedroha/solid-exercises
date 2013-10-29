package com.theladders.solid.lsp.mapping;

public class InsecurePropertyMap extends PropertyMap implements HomeProperties
{
  public InsecurePropertyMap()
  {
    this.put(HomeProperties.AUTO_HOME, "home");
    this.put(MEMBER_HOME, "insecureMemberHome");
    this.put(GUEST_HOME, "insecureGuestHome");
  }
}
