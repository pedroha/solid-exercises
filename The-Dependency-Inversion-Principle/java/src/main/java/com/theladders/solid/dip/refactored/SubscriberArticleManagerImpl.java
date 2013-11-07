package com.theladders.solid.dip.refactored;

import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private ContentRepository     repositoryManager;

  private SuggestedArticleStore suggestedArticleStore;

  public SubscriberArticleManagerImpl(SuggestedArticleStore suggestedArticleStore,
                                      ContentRepository repositoryManager)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.repositoryManager = repositoryManager;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber,
                                                        List<ArticleStatus> statusList,
                                                        ArticleSource source)
  {
    return suggestedArticleStore.getArticlesBySubscriber(subscriber, statusList, source);
  }

  public int addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return suggestedArticleStore.insert(suggestedArticle);
  }

  public void updateNote(SuggestedArticle suggestedArticle,
                         String note)
  {
    suggestedArticleStore.updateNote(suggestedArticle, note);
  }

  public void markRecomDeleted(SuggestedArticle suggestedArticle)
  {
    suggestedArticleStore.markDeleted(suggestedArticle);
  }
}
