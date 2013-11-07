package com.theladders.solid.dip.refactored;

public enum ArticleSource
{
  HTP_CONSULTANT(1);
  
  ArticleSource(int id)
  {
    this.id = id;
  }
  public int id;
}
