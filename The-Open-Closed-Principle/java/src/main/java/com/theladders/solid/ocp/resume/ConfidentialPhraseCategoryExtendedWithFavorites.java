package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseCategoryExtendedWithFavorites extends ConfidentialPhraseCategory
{
  public final static ConfidentialPhraseCategory FavouritePet      = new ConfidentialPhraseCategoryExtendedWithFavorites(280, "FavouritePet");
  public final static ConfidentialPhraseCategory FavouriteFood     = new ConfidentialPhraseCategoryExtendedWithFavorites(281, "FavouriteFood");
  public final static ConfidentialPhraseCategory FavouriteSport    = new ConfidentialPhraseCategoryExtendedWithFavorites(282, "FavouriteSport");
  public final static ConfidentialPhraseCategory FavouriteTVShow   = new ConfidentialPhraseCategoryExtendedWithFavorites(283, "FavouriteTVShow");

  ConfidentialPhraseCategoryExtendedWithFavorites(int id, String name)
  {
    super(id, name, ConfidentialPhraseCategoryType.FAVORITE);
  }
}
