package com.theladders.solid.dip.refactored;

import java.util.List;

public interface ContentRepository
{
  ContentNode getContentNode(SuggestedArticle article);

  public List<? extends SuggestedArticle> resolveArticles(List<? extends SuggestedArticle> articles);
}
