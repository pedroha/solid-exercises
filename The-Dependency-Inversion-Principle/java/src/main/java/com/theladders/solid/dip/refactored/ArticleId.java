package com.theladders.solid.dip.refactored;

public class ArticleId
{
  private int id;
  
  public ArticleId(int id)
  {
    this.id = id;
  }
  
  public int value()
  {
    return id;
  }
}
