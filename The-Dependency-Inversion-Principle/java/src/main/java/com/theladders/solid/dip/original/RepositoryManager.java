package com.theladders.solid.dip.original;

public class RepositoryManager
{
  public ContentNode getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();

    node.addProperty("uuid", id);

    return node;
  }
}
