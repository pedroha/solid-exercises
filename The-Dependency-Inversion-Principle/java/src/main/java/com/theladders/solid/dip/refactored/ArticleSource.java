package com.theladders.solid.dip.refactored;

public enum ArticleSource
{
  HTP_CONSULTANT(1);
  
  public final int id;
  
  ArticleSource(int id)
  {
    this.id = id;
  }
}
