package com.theladders.solid.dip.refactored;

public enum ArticleStatus
{
  UNREAD(1), VIEWED(2), DELETED(4);
  
  ArticleStatus(int id)
  {
    this.id = id;
  }

  public final int id;
}
