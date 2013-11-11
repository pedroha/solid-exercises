package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private SuggestedArticleStore suggestedArticleStore;
  private ContentRepository     articleContentRepository;

  public SubscriberArticleManagerImpl(SuggestedArticleStore suggestedArticleStore,
                                      RepositoryManager repositoryManager)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.articleContentRepository = new ArticleContentRepository(repositoryManager);
  }
  
  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber)
  {
    List<ArticleStatus> statusList = new ArrayList<>();
    statusList.add(ArticleStatus.UNREAD);
    statusList.add(ArticleStatus.VIEWED);
    
    ArticleSource source = ArticleSource.HTP_CONSULTANT;
    return getArticlesbySubscriber(subscriber, statusList, source);
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber,
                                                        List<ArticleStatus> statusList,
                                                        ArticleSource source)
  {
    List<SuggestedArticle> articles = suggestedArticleStore.getArticlesBySubscriber(subscriber, statusList, source);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    for (SuggestedArticle article : articles)
    {
      ContentNode node = articleContentRepository.getContentNode(article);
      if (node != null)
      {
        article.setContent(node);
      }
    }
    return articles;
  }

  public void addSuggestedArticle(SuggestedArticle article)
  {
    article.setSuggestedArticleStatusId(ArticleStatus.UNREAD.id);
    article.setSuggestedArticleSourceId(ArticleSource.HTP_CONSULTANT.id);
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date

    suggestedArticleStore.insert(article);
  }
  
  public void updateNote(SuggestedArticle suggestedArticle, String note)
  {
    suggestedArticle.setNote(note);
    suggestedArticleStore.updateNote(suggestedArticle);
  }

  public void markRecomDeleted(SuggestedArticle suggestedArticle)
  {
    suggestedArticle.setSuggestedArticleStatusId(ArticleStatus.DELETED.id);
    suggestedArticleStore.updateStatus(suggestedArticle);
  }
}
