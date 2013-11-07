package com.theladders.solid.dip.refactored;

import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private SuggestedArticleDao              suggestedArticleDao;
  private ContentRepository                repositoryManager;
  
  private SuggestedArticleStore            suggestedArticleStore;

  public SubscriberArticleManagerImpl(SuggestedArticleDao suggestedArticleDao,
                                      ContentRepository repositoryManager)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.repositoryManager = repositoryManager;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber)
  {
    return suggestedArticleStore.getArticlesBySubscriber(subscriber);    
  }

  public int addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return suggestedArticleStore.insert(suggestedArticle);
  }

  public void updateNote(SuggestedArticle suggestedArticle, String note)
  {
    suggestedArticleStore.updateNote(suggestedArticle, note);
  }

  public void markRecomDeleted(SuggestedArticle suggestedArticle)
  {
    suggestedArticleStore.markDeleted(suggestedArticle);
  }
}
