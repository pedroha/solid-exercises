package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private SuggestedArticleStore suggestedArticleStore;
  private ContentRepository     articleContentRepository;

  public SubscriberArticleManagerImpl(SuggestedArticleStore suggestedArticleStore,
                                      ContentRepository articleContentRepository)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.articleContentRepository = articleContentRepository;
  }

  public void addSuggestedArticle(SuggestedArticle article)
  {
    article.setArticleStatus(ArticleStatus.UNREAD);
    article.setArticleSource(ArticleSource.HTP_CONSULTANT); // Why harcoded in initial code to = 1? // Always consultant !?
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date

    suggestedArticleStore.insert(article);
  }

  public List<? extends SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    List<ArticleStatus> statusList = new ArrayList<>();
    statusList.add(ArticleStatus.UNREAD);
    statusList.add(ArticleStatus.VIEWED);

    ArticleSource source = ArticleSource.HTP_CONSULTANT;
    return getArticlesbySubscriber(subscriber, statusList, source);
  }

  private List<? extends SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber,
                                                                   List<ArticleStatus> statusList,
                                                                   ArticleSource source)
  {
    List<? extends SuggestedArticle> articles = suggestedArticleStore.getArticlesBySubscriber(subscriber,
                                                                                              statusList,
                                                                                              source);
    // Fetch content associated with SuggestedArticle
    return articleContentRepository.resolveArticles(articles);
  }
}
