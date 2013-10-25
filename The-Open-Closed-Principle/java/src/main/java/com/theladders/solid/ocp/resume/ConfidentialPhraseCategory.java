package com.theladders.solid.ocp.resume;

public enum ConfidentialPhraseCategory implements ContactInfo
{
  Name(76),
  MailingAddress(79, true),
  PhoneNumber(78, true),
  EmailAddress(77, true),
  ContactInfo(80, true),
  CompanyName(81),
  WorkExperience(82),
  FavoritePet(101);

  @SuppressWarnings("unused")
  private int id;
  private boolean myContactInfo;

  ConfidentialPhraseCategory(int id, boolean contactInfo)
  {
    this.id = id;
    this.myContactInfo = contactInfo;
  }
  
  ConfidentialPhraseCategory(int id)
  {
    this(id, false);
  }

  public boolean isContactInfo()
  {
    return myContactInfo;
  }
}
