package com.theladders.solid.dip.refactored;

import java.util.List;

public interface SuggestedArticleStore
{
  List<SuggestedArticle> getArticlesBySubscriber(Jobseeker subscriber,
                                                 List<ArticleStatus> statusList,
                                                 ArticleSource source);
  int insert(SuggestedArticle article);

  void updateNote(SuggestedArticle suggestedArticle);

  void updateStatus(SuggestedArticle suggestedArticle);
}
