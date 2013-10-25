package com.theladders.solid.ocp.resume;

import java.util.List;

public class ConfidentialPhraseCategory
{
  private static PhraseListContainer phraseList = new PhraseListContainer();

  public final static ConfidentialPhraseCategory Name           = new ConfidentialPhraseCategory(76, "Name");
  public final static ConfidentialPhraseCategory MailingAddress = new ConfidentialPhraseCategory(79, "MailingAddress", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory PhoneNumber    = new ConfidentialPhraseCategory(78, "PhoneNumber", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory EmailAddress   = new ConfidentialPhraseCategory(77, "EmailAddress", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory ContactInfo    = new ConfidentialPhraseCategory(80, "ContactInfo", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory CompanyName    = new ConfidentialPhraseCategory(81, "CompanyName");
  public final static ConfidentialPhraseCategory WorkExperience = new ConfidentialPhraseCategory(82, "WorkExperience");

  @SuppressWarnings("unused")
  private int                                    id;
  private String                                 name;
  private ConfidentialPhraseCategoryType type;
  
  ConfidentialPhraseCategory(int id,
                             String name,
                             ConfidentialPhraseCategoryType type)
  {
    this.id = id;
    this.name= name;
    this.type = type;
    add(this);
  }

  ConfidentialPhraseCategory(int id, String name)
  {
    this(id, name, ConfidentialPhraseCategoryType.DEFAULT);
  }

  @Override
  public String toString()
  {    
    return name;
  }

  public String name()
  {
    return toString();
  }

  public boolean isType(ConfidentialPhraseCategoryType type)
  {
    return this.type == type;
  }
  
  protected void add(ConfidentialPhraseCategory category)
  {
    phraseList.add(category);
  }

  public static List<ConfidentialPhraseCategory> values()
  {
    return phraseList.values();
  }
}
