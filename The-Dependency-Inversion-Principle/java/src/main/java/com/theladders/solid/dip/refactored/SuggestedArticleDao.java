package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

  public int insert(SuggestedArticle article)
  {
    article.setSuggestedArticleStatusId(ArticleStatus.UNREAD.id);
    article.setSuggestedArticleSourceId(ArticleSource.HTP_CONSULTANT.id);

    Date date = new Date();
    article.setCreateTime(date); // current date
    article.setUpdateTime(date); // current date
    int newId = insertReturnId(article);
    return newId;
  }

  public void updateNote(SuggestedArticle suggestedArticle)
  {
    int id = suggestedArticle.getSuggestedArticleId();
    
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setNote(suggestedArticle.getNote());

    updateByPrimaryKeySelective(article);
  }

  public void updateStatus(SuggestedArticle suggestedArticle)
  {
    SuggestedArticle article = new SuggestedArticle();
    article.setSubscriberId(suggestedArticle.getSuggestedArticleId());
    article.setSuggestedArticleStatusId(suggestedArticle.getSuggestedArticleStatusId());
    
    updateByPrimaryKeySelective(article);
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
