package com.theladders.solid.dip.refactored;

public class RepositoryManager implements ContentRepository
{ 
  public ContentNode getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();

    node.addProperty("uuid", id);

    return node;
  }
}
