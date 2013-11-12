package com.theladders.solid.dip.refactored;

import java.util.Date;

public interface SuggestedArticle
{
  String getArticleExternalIdentifier();

  void setSuggestedArticleId(ArticleId id);
  ArticleId getSuggestedArticleId();
 
  void setContent(ContentNode node);
  ContentNode getContent();
  
  void setNote(String note);
  String getNote();
  
  void setArticleStatus(ArticleStatus status);
  void setArticleSource(ArticleSource source);
  void setCreateTime(Date date);
  void setUpdateTime(Date date);
  
  ArticleStatus getArticleStatus();
  ArticleSource getArticleSource();
}
