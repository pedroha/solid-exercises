package com.theladders.solid.dip.refactored;

import java.util.List;

public class ArticleContentRepository implements ContentRepository
{
  private RepositoryManager      repositoryManager;
  private CategoryResourceMapper resourceMapper;

  public ArticleContentRepository(RepositoryManager repositoryManager)
  {
    this.repositoryManager = repositoryManager;
    this.resourceMapper = new CategoryResourceMapper();
  }
  
  public List<? extends SuggestedArticle> resolveArticles(List<? extends SuggestedArticle> articles)
  {
    for (SuggestedArticle article : articles)
    {
      ContentNode node = getContentNode(article);
      if (node != null && ContentUtils.isPublishedAndEnabled(node))
      {
        // Override miniImagePath
        overrideMiniImagePath(node);
        article.setContent(node);
      }
    }
    return articles;
  }

  public ContentNode getContentNode(SuggestedArticle article)
  {
    String uuid = article.getArticleExternalIdentifier();

    // Attempt to fetch the actual content;
    return repositoryManager.getNodeByUuid(uuid);
  }

  private void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", resourceMapper.getImagePath(category));
    }
  }
}
