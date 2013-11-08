package com.theladders.solid.dip.refactored;

public enum ArticleSource
{
  HTP_CONSULTANT(1);
  
  public final int id;
  
  ArticleSource(int id)
  {
    this.id = id;
  }
  
  public static ArticleSource getById(int id)
  {
    for (ArticleSource source: ArticleSource.values())
    {
      if (source.id == id)
      {
        return source;
      }
    }
    return null;
  }
}
