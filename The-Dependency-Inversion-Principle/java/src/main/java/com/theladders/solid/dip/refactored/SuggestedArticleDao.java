package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao implements SuggestedArticleStore
{
  public SuggestedArticleDao()
  {
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

    return dbSuggestions;
  }

  public void insert(SuggestedArticle article)
  {
    article.setSuggestedArticleId(insertReturnId(article));
  }

  public void updateNote(SuggestedArticle suggestedArticle)
  {
    int articleId = suggestedArticle.getSuggestedArticleId();
    
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(articleId);
    article.setNote(suggestedArticle.getNote());

    updateByPrimaryKeySelective(article);
  }

  public void updateStatus(SuggestedArticle suggestedArticle)
  {
    int articleId = suggestedArticle.getSuggestedArticleId();
    int statusId = suggestedArticle.getSuggestedArticleStatusId();
    
    SuggestedArticle article = new SuggestedArticle();
    article.setSubscriberId(articleId);
    article.setSuggestedArticleStatusId(statusId);

    updateByPrimaryKeySelective(article);
  }

  public static List<SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }

  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticle article) {}

  private static int insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return 0;
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
  
  /*
  public void update(SuggestedArticle suggestedArticle)
  {
    int articleId = suggestedArticle.getSuggestedArticleId();
    int statusId = suggestedArticle.getSuggestedArticleStatusId();
    
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(articleId);
    article.setSuggestedArticleStatusId(statusId);
    article.setNote(suggestedArticle.getNote());

    updateByPrimaryKeySelective(article);
  }
  */  
}
