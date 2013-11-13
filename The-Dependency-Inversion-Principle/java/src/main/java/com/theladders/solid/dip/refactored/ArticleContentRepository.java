package com.theladders.solid.dip.refactored;

public class ArticleContentRepository implements ContentRepository
{
  private RepositoryManager repositoryManager;
  private ResourceMapper    resourceMapper;

  public ArticleContentRepository(RepositoryManager repositoryManager,
                                  ResourceMapper resourceMapper)
  {
    this.repositoryManager = repositoryManager;
    this.resourceMapper = resourceMapper;
  }
  
  public void resolveContent(SuggestedArticle article)
  {
    ContentNode node = getContentNode(article);
    if (node != null && ContentUtils.isPublishedAndEnabled(node))
    {
      // Override miniImagePath
      overrideMiniImagePath(node);
      article.setContent(node);
    }
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
      node.addProperty("miniImagePath", resourceMapper.getResourcePathByCategory(category));
    }
  }
}
