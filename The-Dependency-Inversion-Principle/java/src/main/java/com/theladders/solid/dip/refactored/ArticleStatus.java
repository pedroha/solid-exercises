package com.theladders.solid.dip.refactored;

public enum ArticleStatus
{
  UNREAD(1), VIEWED(2), DELETED(4);
  
  public final int id;

  ArticleStatus(int id)
  {
    this.id = id;
  }
}
