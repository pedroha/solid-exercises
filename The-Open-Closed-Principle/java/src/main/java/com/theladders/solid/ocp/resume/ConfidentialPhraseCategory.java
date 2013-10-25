package com.theladders.solid.ocp.resume;

import java.util.List;

import com.theladders.solid.ocp.phrase.category.PhraseListContainer;

public class ConfidentialPhraseCategory
{
  private static PhraseListContainer phraseList = new PhraseListContainer();

  public final static ConfidentialPhraseCategory Name           = new ConfidentialPhraseCategory(76, "Name");
  public final static ConfidentialPhraseCategory MailingAddress = new ConfidentialPhraseCategory(79, "MailingAddress", true);
  public final static ConfidentialPhraseCategory PhoneNumber    = new ConfidentialPhraseCategory(78, "PhoneNumber", true);
  public final static ConfidentialPhraseCategory EmailAddress   = new ConfidentialPhraseCategory(77, "EmailAddress",true);
  public final static ConfidentialPhraseCategory ContactInfo    = new ConfidentialPhraseCategory(80, "ContactInfo",true);
  public final static ConfidentialPhraseCategory CompanyName    = new ConfidentialPhraseCategory(81, "CompanyName");
  public final static ConfidentialPhraseCategory WorkExperience = new ConfidentialPhraseCategory(82, "WorkExperience");

  @SuppressWarnings("unused")
  private int                                    id;
  private boolean                                myContactInfo;
  private String                                 name;
  
  ConfidentialPhraseCategory(int id,
                             String name,
                             boolean contactInfo)
  {
    this.id = id;
    this.name= name;
    this.myContactInfo = contactInfo;
    add(this);
  }

  ConfidentialPhraseCategory(int id, String name)
  {
    this(id, name, false);
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

  public boolean isContactInfo()
  {
    return myContactInfo;
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
