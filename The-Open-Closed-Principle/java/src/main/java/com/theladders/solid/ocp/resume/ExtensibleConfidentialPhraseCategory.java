package com.theladders.solid.ocp.resume;

public class ExtensibleConfidentialPhraseCategory extends ConfidentialPhraseCategory
{
  public final static ConfidentialPhraseCategory FavouritePet    = new ConfidentialPhraseCategory(80, "FavouritePet", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory FavouriteFood   = new ConfidentialPhraseCategory(81, "FavouriteFood");

  ExtensibleConfidentialPhraseCategory(int id, String name)
  {
    super(id, name);
  }
}
