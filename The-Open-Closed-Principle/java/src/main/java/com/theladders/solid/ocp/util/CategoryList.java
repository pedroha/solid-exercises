package com.theladders.solid.ocp.util;

import java.util.LinkedList;
import java.util.List;

public class CategoryList
{
  private List<Category> categories = new LinkedList<Category>();
  
  private CategoryList()
  {
  }
  
  public void add(Category category)
  {
    categories.add(category);
  }
  
  public List<Category>getCategories()
  {
    return categories;
  }
}
