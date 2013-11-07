package com.theladders.solid.dip.refactored;

import java.util.List;

public interface SuggestedArticleStore
{
  /*
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticle article) {}

  public int insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return 0;
  }

  public List<SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }
  */
  
  List<SuggestedArticle> getArticlesBySubscriber(Jobseeker jobseeker);

  int insert(SuggestedArticle article);

  void updateNote(SuggestedArticle suggestedArticle, String note);
  
  void markDeleted(SuggestedArticle suggestedArticle);
}
