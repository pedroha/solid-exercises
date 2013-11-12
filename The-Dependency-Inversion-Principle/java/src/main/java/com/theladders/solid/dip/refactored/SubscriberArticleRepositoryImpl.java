package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberArticleRepositoryImpl implements SubscriberArticleRepository
{
  private SuggestedArticleStore suggestedArticleStore;
  private ContentRepository     articleContentRepository;

  public void addSuggestedArticle(SuggestedArticle article)
  {
    article.setArticleStatus(ArticleStatus.UNREAD);
    article.setArticleSource(ArticleSource.HTP_CONSULTANT); // Why harcoded in initial code to = 1? Always consultant !?
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date
    
    suggestedArticleStore.insert(article);
  }

  public SubscriberArticleRepositoryImpl(SuggestedArticleStore suggestedArticleStore,
                                         RepositoryManager repositoryManager)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.articleContentRepository = new ArticleContentRepository(repositoryManager);
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    List<ArticleStatus> statusList = new ArrayList<>();
    statusList.add(ArticleStatus.UNREAD);
    statusList.add(ArticleStatus.VIEWED);

    ArticleSource source = ArticleSource.HTP_CONSULTANT;
    return getArticlesbySubscriber(subscriber, statusList, source);
  }

  private List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber,
                                                         List<ArticleStatus> statusList,
                                                         ArticleSource source)
  {
    List<SuggestedArticle> articles = suggestedArticleStore.getArticlesBySubscriber(subscriber, statusList, source);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    resolveArticles(articles);

    return articles;
  }

  private void resolveArticles(List<SuggestedArticle> articles)
  {
    for (SuggestedArticle article : articles)
    {
      ContentNode node = articleContentRepository.getContentNode(article);
      if (node != null && ContentUtils.isPublishedAndEnabled(node))
      {
        article.setContent(node);
      }
    }
  }
}
