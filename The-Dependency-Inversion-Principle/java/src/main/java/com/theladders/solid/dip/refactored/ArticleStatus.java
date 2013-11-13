package com.theladders.solid.dip.refactored;

public enum ArticleStatus
{
  UNREAD(1), VIEWED(2), DELETED(4);
  
  public final int id;

  ArticleStatus(int id)
  {
    this.id = id;
  }

  public static ArticleStatus getById(int id)
  {
    for (ArticleStatus source: ArticleStatus.values())
    {
      if (source.id == id)
      {
        return source;
      }
    }
    return null; // throw Exception instead? Maybe()?
  }
}
