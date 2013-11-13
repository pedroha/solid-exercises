package com.theladders.solid.dip.refactored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao implements SuggestedArticleStore
{
  public SuggestedArticleDao()
  {
  }

  public List<? extends SuggestedArticle> getArticlesBySubscriber(Subscriber subscriber,
                                                                  ArticleStatus[] statusList,
                                                                  ArticleSource source)
  {
    int subscriberId = subscriber.getSubscriberId();

    SuggestedArticleExample criteria = new SuggestedArticleExample();
    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId)
            .andSuggestedArticleStatusIdIn(getStatusIdList(statusList)) // must
            .andSuggestedArticleSourceIdEqualTo(source.id);

    criteria.setOrderByClause("create_time desc");

    List<? extends SuggestedArticle> dbSuggestions = selectByExampleWithBlobs(criteria);
    return dbSuggestions;
  }

  public void insert(SuggestedArticle article)
  {
    ArticleId id = new ArticleId(insertReturnId(article));
    article.setSuggestedArticleId(id);
  }

  public void updateNote(SuggestedArticle suggestedArticle)
  {
    ArticleId id = suggestedArticle.getSuggestedArticleId();

    SuggestedArticleSQL article = new SuggestedArticleSQL();
    article.setSuggestedArticleId(id);
    article.setNote(suggestedArticle.getNote());

    updateByPrimaryKeySelective(article);
  }

  public void updateStatus(SuggestedArticle suggestedArticle)
  {
    ArticleId id = suggestedArticle.getSuggestedArticleId();
    ArticleStatus status = suggestedArticle.getArticleStatus();

    SuggestedArticleSQL article = new SuggestedArticleSQL();
    article.setSuggestedArticleId(id);
    article.setArticleStatus(status);

    updateByPrimaryKeySelective(article);
  }

  public static List<? extends SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticleSQL());
  }

  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticleSQL article)
  {
  }

  private static int insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return 0;
  }

  private static List<Integer> getStatusIdList(ArticleStatus[] statusArray)
  {
    List<Integer> list = new ArrayList<>();
    for (ArticleStatus status : statusArray)
    {
      list.add(status.id);
    }
    return list;
  }
}
