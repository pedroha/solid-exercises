package com.theladders.solid.dip.refactored;

import java.util.HashMap;
import java.util.Map;

public class ArticleContentRepository implements ContentRepository
{
  private String IMAGE_PREFIX = "http://somecdnprodiver.com/static/images/careerAdvice/";

  private RepositoryManager     repositoryManager;
  private ResourceMapper        resourceMapper;
  
  public ArticleContentRepository(RepositoryManager repositoryManager)
  {
    this.repositoryManager = repositoryManager;
    this.resourceMapper = new ResourceMapper(IMAGE_PREFIX);
  }
  
  public ContentNode getContentNode(SuggestedArticle article)
  {
    String uuid = article.getArticleExternalIdentifier();
    
    // Attempt to fetch the actual content;
    ContentNode node = repositoryManager.getNodeByUuid(uuid);
    
    if (node != null && ContentUtils.isPublishedAndEnabled(node))
    {
      // Override miniImagePath
      overrideMiniImagePath(node);
    }
    return node;    
  }

  private void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", resourceMapper.getImagePath(category));
//      node.addProperty("miniImagePath", IMAGE_PREFIX + CATEGORY_IMAGE_MAP.get(category));
    }
  }
}
