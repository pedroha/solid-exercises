package com.theladders.solid.dip.refactored;

import java.util.Date;

import com.theladders.solid.dip.original.Column;

// A SuggestedArticle is one instance of an article that has been
// recommended to a particular subscriber.

public class SuggestedArticleSQL implements SuggestedArticle
{
  private ArticleId     suggestedArticleId;
  private Integer       subscriberId;
  private ArticleSource articleSource;
  private String        articleExternalIdentifier;
  private ArticleStatus articleStatus;
  private Date          createTime;
  private Integer       creatorId;
  private Date          updateTime;
  private Integer       updaterId;
  private String        note;
  private ContentNode   content;

  public SuggestedArticleSQL()
  {}

  public SuggestedArticleSQL(Subscriber subscriber,
                             String articleExternalIdentifier,
                             String note,
                             Integer adminUserId)
  {
    this.subscriberId = subscriber.getSubscriberId();
    this.articleExternalIdentifier = articleExternalIdentifier;
    this.articleStatus = ArticleStatus.UNREAD; // STATUS_UNREAD = 1
    this.articleSource = ArticleSource.NONE; // = 1
    this.note = note;
    this.creatorId = adminUserId;
    this.createTime = new Date(); // current date
    this.updateTime = new Date(); // current date
  }

  @Column(name = "suggested_article_id")
  public ArticleId getSuggestedArticleId()
  {
    return this.suggestedArticleId;
  }

  public void setSuggestedArticleId(ArticleId suggestedArticleId)
  {
    this.suggestedArticleId = suggestedArticleId;
  }

  @Column(name = "subscriber_id")
  public Integer getSubscriberId()
  {
    return subscriberId;
  }

  public void setSubscriberId(Integer subscriberId)
  {
    this.subscriberId = subscriberId;
  }

  // Column(name = "suggested_article_source_id")
  public ArticleSource getArticleSource()
  {
    return articleSource;
  }

  public void setArticleSource(ArticleSource suggestedArticleSourceId)
  {
    this.articleSource = suggestedArticleSourceId;
  }

  @Column(name = "article_external_identifier")
  public String getArticleExternalIdentifier()
  {
    return articleExternalIdentifier;
  }

  public void setArticleExternalIdentifier(String articleExternalIdentifier)
  {
    this.articleExternalIdentifier = articleExternalIdentifier == null ? null : articleExternalIdentifier.trim();
  }

  // Column(name = "suggested_article_status_id")
  public ArticleStatus getArticleStatus()
  {
    return articleStatus;
  }

  public void setArticleStatus(ArticleStatus articleStatus)
  {
    this.articleStatus = articleStatus;
  }

  @Column(name = "create_time")
  public Date getCreateTime()
  {
    return createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  @Column(name = "creator_id")
  public Integer getCreatorId()
  {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId)
  {
    this.creatorId = creatorId;
  }

  @Column(name = "update_time")
  public Date getUpdateTime()
  {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime)
  {
    this.updateTime = updateTime;
  }

  @Column(name = "updater_id")
  public Integer getUpdaterId()
  {
    return updaterId;
  }

  public void setUpdaterId(Integer updaterId)
  {
    this.updaterId = updaterId;
  }

  @Column(name = "note")
  public String getNote()
  {
    return note;
  }

  public void setNote(String note)
  {
    this.note = note == null ? null : note.trim();
  }

  public ContentNode getContent()
  {
    return content;
  }

  public void setContent(ContentNode node)
  {
    this.content = node;
  }

  public boolean getIsRead()
  {
    return (getArticleStatus() == ArticleStatus.VIEWED); // VIEWED = 2
  }

  public void setIsRead(boolean read)
  {
    ArticleStatus status = (read ? ArticleStatus.VIEWED : ArticleStatus.UNREAD);
    setArticleStatus(status);
  }

  @Column(name = "suggested_article_source_id")
  public void setArticleSourceId(int id)
  {
    setArticleSource(ArticleSource.getById(id));
  }
  
  public int getArticleSourceId()
  {
    return articleSource.id;
  }
  
  @Column(name = "suggested_article_status_id")
  public void setArticleStatusId(int id)
  {
    setArticleStatus(ArticleStatus.getById(id));
  }
  
  public int getArticleStatusId()
  {
    return articleStatus.id;
  }
}
