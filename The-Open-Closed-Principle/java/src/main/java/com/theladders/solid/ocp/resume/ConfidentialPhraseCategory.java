package com.theladders.solid.ocp.resume;

import java.lang.reflect.Field;
import java.util.List;

import com.theladders.solid.ocp.phrase.category.PhraseListContainer;

public class ConfidentialPhraseCategory
{
  public final static ConfidentialPhraseCategory Name           = new ConfidentialPhraseCategory(76);
  public final static ConfidentialPhraseCategory MailingAddress = new ConfidentialPhraseCategory(79, true);
  public final static ConfidentialPhraseCategory PhoneNumber    = new ConfidentialPhraseCategory(78, true);
  public final static ConfidentialPhraseCategory EmailAddress   = new ConfidentialPhraseCategory(77, true);
  public final static ConfidentialPhraseCategory ContactInfo    = new ConfidentialPhraseCategory(80, true);
  public final static ConfidentialPhraseCategory CompanyName    = new ConfidentialPhraseCategory(81);
  public final static ConfidentialPhraseCategory WorkExperience = new ConfidentialPhraseCategory(82);

  @SuppressWarnings("unused")
  private int                                    id;
  private boolean                                myContactInfo;

  ConfidentialPhraseCategory(int id,
                             boolean contactInfo)
  {
    this.id = id;
    this.myContactInfo = contactInfo;
    PhraseListContainer.add(this);
  }

  ConfidentialPhraseCategory(int id)
  {
    this(id, false);
  }

  @Override
  public String toString()
  {
    Field[] fields = getClass().getDeclaredFields();
    return fields[PhraseListContainer.indexOf(this)].getName();
  }

  public String name()
  {
    return toString();
  }

  public boolean isContactInfo()
  {
    return myContactInfo;
  }

  public static List<ConfidentialPhraseCategory> values()
  {
    return PhraseListContainer.values();
  }
}
