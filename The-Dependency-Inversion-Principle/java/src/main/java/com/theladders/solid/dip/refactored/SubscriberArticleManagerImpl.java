package com.theladders.solid.dip.refactored;

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

  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber,
                                                        List<ArticleStatus> statusList,
                                                        ArticleSource source)
  {
    List<SuggestedArticle> articles = suggestedArticleStore.getArticlesBySubscriber(subscriber, statusList, source);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    resolveArticles(articles);

    return articles;
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
