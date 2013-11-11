package com.theladders.solid.dip.refactored;

import java.util.List;

public interface SuggestedArticleStore
{
  void insert(SuggestedArticle article);
  
  List<SuggestedArticle> getArticlesBySubscriber(Jobseeker subscriber,
                                                 List<ArticleStatus> statusList,
                                                 ArticleSource source);
/*  void update(SuggestedArticle article); */
  void updateNote(SuggestedArticle article);
  void updateStatus(SuggestedArticle article);
}
