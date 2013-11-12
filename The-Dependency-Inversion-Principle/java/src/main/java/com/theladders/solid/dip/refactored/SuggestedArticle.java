package com.theladders.solid.dip.refactored;

import java.util.Date;

public interface SuggestedArticle
{
  String getArticleExternalIdentifier();
 
  void setContent(ContentNode node);
  ContentNode getContent();
  
  String getNote();
  
  void setArticleStatus(ArticleStatus status);
  void setArticleSource(ArticleSource source);
  void setCreateTime(Date date);
  void setUpdateTime(Date date);
  
  void setSuggestedArticleId(int id);
  int getSuggestedArticleId();
  
  ArticleStatus getArticleStatus();
  ArticleSource getArticleSource();
}
