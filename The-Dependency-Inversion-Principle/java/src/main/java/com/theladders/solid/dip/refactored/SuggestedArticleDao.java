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
                                                                  List<ArticleStatus> statusList,
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
    article.setSuggestedArticleId(insertReturnId(article));
  }

  public void updateNote(SuggestedArticle suggestedArticle)
  {
    int articleId = suggestedArticle.getSuggestedArticleId();

    SuggestedArticleSQL article = new SuggestedArticleSQL();
    article.setSuggestedArticleId(articleId);
    article.setNote(suggestedArticle.getNote());

    updateByPrimaryKeySelective(article);
  }

  public void updateStatus(SuggestedArticle suggestedArticle)
  {
    int articleId = suggestedArticle.getSuggestedArticleId();
    ArticleStatus status = suggestedArticle.getArticleStatus();

    SuggestedArticleSQL article = new SuggestedArticleSQL();
    article.setSuggestedArticleId(articleId);
    article.setSuggestedArticleStatusId(status.id);

    updateByPrimaryKeySelective(article);
  }

  public static List<? extends SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return (List<? extends SuggestedArticle>) Collections.singletonList(new SuggestedArticleSQL());
  }

  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticleSQL article)
  {}

  private static int insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return 0;
  }

  private static List<Integer> getStatusIdList(List<ArticleStatus> statusList)
  {
    List<Integer> list = new ArrayList<>();
    for (ArticleStatus status : statusList)
    {
      list.add(status.id);
    }
    return list;
  }

  /*
   * public void update(SuggestedArticle suggestedArticle) { int articleId =
   * suggestedArticle.getSuggestedArticleId(); int statusId =
   * suggestedArticle.getSuggestedArticleStatusId(); SuggestedArticle article = new
   * SuggestedArticle(); article.setSuggestedArticleId(articleId);
   * article.setSuggestedArticleStatusId(statusId); article.setNote(suggestedArticle.getNote());
   * updateByPrimaryKeySelective(article); }
   */
}
