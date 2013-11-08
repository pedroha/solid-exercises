package com.theladders.solid.dip.refactored;

import java.util.List;

public interface SubscriberArticleManager
{
  /**
   * Get a list of all active suggested articles for a given subscriber.
   *
   *   Sorted reverse chronological by time recommended
   *
   * It also gets the content of the actual articles from the CMS and stores it in
   * the SuggestedArticle object.
   *
   * @param subscriber
   *          
   * @return List of all suggested articles whose status is either New or Viewed
   */
  public List<SuggestedArticle> getArticlesbySubscriber(Jobseeker subscriber,
                                                        List<ArticleStatus> statusList,
                                                        ArticleSource source);

  /**
   * Add a SuggestedArticle to the database.
   *
   * @param suggestedArticle
   */

  public int addSuggestedArticle(SuggestedArticle suggestedArticle);

  /**
   * Update the note of the Suggested Article
   *  with the note passed in.
   *
   */
  public void updateNote(SuggestedArticle suggestedArticle, String note);

  /**
   * Mark as deleted the Suggested Article
   *
   */
  public void markRecomDeleted(SuggestedArticle suggestedArticle);
}