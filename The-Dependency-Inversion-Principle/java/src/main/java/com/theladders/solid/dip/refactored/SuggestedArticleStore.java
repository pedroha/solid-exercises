package com.theladders.solid.dip.refactored;

import java.util.List;

public interface SuggestedArticleStore
{
  List<SuggestedArticle> getArticlesBySubscriber(Jobseeker subscriber,
                                                 List<ArticleStatus> statusList,
                                                 ArticleSource source);
  void insert(SuggestedArticle article);
  void update(SuggestedArticle article);
}
