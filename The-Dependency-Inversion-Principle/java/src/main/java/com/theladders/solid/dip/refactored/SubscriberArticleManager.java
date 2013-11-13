package com.theladders.solid.dip.refactored;

import java.util.Date;
import java.util.List;

public class SubscriberArticleManager implements SubscriberArticles
{
  private SuggestedArticleStore suggestedArticleStore;
  private ContentRepository     articleContentRepository;

  public SubscriberArticleManager(SuggestedArticleStore suggestedArticleStore,
                                      ContentRepository articleContentRepository)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.articleContentRepository = articleContentRepository;
  }

  public void addSuggestedArticle(SuggestedArticle article)
  {
    article.setArticleStatus(ArticleStatus.UNREAD);
    article.setArticleSource(ArticleSource.HTP_CONSULTANT);
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date

    suggestedArticleStore.insert(article);
  }

  public List<? extends SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    ArticleStatus[] status = {ArticleStatus.UNREAD, ArticleStatus.VIEWED};
    return getArticlesbySubscriber(subscriber, status, ArticleSource.HTP_CONSULTANT);
  }

  private List<? extends SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber,
                                                                   ArticleStatus[] status,
                                                                   ArticleSource source)
  {
    List<? extends SuggestedArticle> articles = suggestedArticleStore.getArticlesBySubscriber(subscriber,
                                                                                              status,
                                                                                              source);
    for (SuggestedArticle article: articles)
    {
      // Fetch content associated with SuggestedArticle
      articleContentRepository.resolveContent(article);
    }
    return articles;
  }
  
  public void updateNote(SuggestedArticle suggestedArticle,
                         String note)
  {
    suggestedArticle.setNote(note);
    suggestedArticleStore.updateNote(suggestedArticle);
  }

  public void markDeleted(SuggestedArticle suggestedArticle)
  {
    suggestedArticle.setArticleStatus(ArticleStatus.DELETED);
    suggestedArticleStore.updateStatus(suggestedArticle);
  }
}
