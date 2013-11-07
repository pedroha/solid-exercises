package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SuggestedArticleDao implements SuggestedArticleStore
{
  private ContentRepository contentRepository;
  
  public SuggestedArticleDao(ContentRepository contentRepository)
  {
    this.contentRepository = contentRepository;
  }
  
  public List<SuggestedArticle> getArticlesBySubscriber(Jobseeker subscriber,
                                                        List<ArticleStatus>statusList,
                                                        ArticleSource source)
  {
    int subscriberId = subscriber.getSubscriberId();
    
    SuggestedArticleExample criteria = new SuggestedArticleExample();
    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId)
            .andSuggestedArticleStatusIdIn(getStatusIdList(statusList)) // must
            .andSuggestedArticleSourceIdEqualTo(source.id);

    criteria.setOrderByClause("create_time desc");
    
    List<SuggestedArticle> dbSuggestions = selectByExampleWithBlobs(criteria);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    resolveArticles(dbSuggestions);

    return dbSuggestions;
  }

  public int insert(SuggestedArticle article)
  {
    Integer STATUS_UNREAD = 1;
    Integer HTP_CONSULTANT = 1;
    article.setSuggestedArticleStatusId(STATUS_UNREAD);
    article.setSuggestedArticleSourceId(HTP_CONSULTANT);
    article.setCreateTime(new Date()); // current date
    article.setUpdateTime(new Date()); // current date
    int newId = insertReturnId(article);
    return newId;
  }

  public void updateNote(SuggestedArticle suggestedArticle, String note)
  {
    int id = suggestedArticle.getSuggestedArticleId();
    
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setNote(note);

    updateByPrimaryKeySelective(article);
  }

  public void markDeleted(SuggestedArticle suggestedArticle)
  {
    int id = suggestedArticle.getSuggestedArticleId();
    Integer STATUS_DELETED = 4;
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setSuggestedArticleStatusId(STATUS_DELETED);
    updateByPrimaryKeySelective(article);
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

  private static List<Integer> getStatusIdList(List<ArticleStatus>statusList)
  {
    List<Integer> list = new ArrayList<>();
    for (ArticleStatus status: statusList)
    {
      list.add(status.id);
    }
    return list;
  }

  private void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticle article) {}

  private static int insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return 0;
  }

  private static List<SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }
}
