package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private SuggestedArticleStore suggestedArticleStore;
  private ContentRepository     contentRepository;

  public SubscriberArticleManagerImpl(SuggestedArticleStore suggestedArticleStore,
                                      ContentRepository contentRepository)
  {
    this.suggestedArticleStore = suggestedArticleStore;
    this.contentRepository = contentRepository;
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
    resolveArticles(articles);

    return articles;
  }

  public int addSuggestedArticle(SuggestedArticle article)
  {
    article.setSuggestedArticleStatusId(ArticleStatus.UNREAD.id);
    article.setSuggestedArticleSourceId(ArticleSource.HTP_CONSULTANT.id);
    Date date = new Date();
    article.setCreateTime(date); // current date
    article.setUpdateTime(date); // current date

    return suggestedArticleStore.insert(article);
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

  private void resolveArticles(List<SuggestedArticle> dbArticles)
  {
    for (SuggestedArticle article : dbArticles)
    {
      // Attempt to fetch the actual content;
      ContentNode content = contentRepository.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        ContentUtils.overrideMiniImagePath(content);
        article.setContent(content);
      }
    }
  }
}
