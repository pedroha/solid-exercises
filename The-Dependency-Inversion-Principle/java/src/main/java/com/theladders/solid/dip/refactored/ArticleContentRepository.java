package com.theladders.solid.dip.refactored;

import java.util.HashMap;
import java.util.Map;

public class ArticleContentRepository implements ContentRepository
{
  private static final String              IMAGE_PREFIX       = "http://somecdnprodiver.com/static/images/careerAdvice/";
  private static final Map<String, String> CATEGORY_IMAGE_MAP = new HashMap<>();

  static
  {
    CATEGORY_IMAGE_MAP.put("Interviewing", "interviewing_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Job Search", "job_search_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Networking", "networking_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Personal Branding", "personalBranding_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Resume", "resume_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Salary", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Assessment", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("On the Job", "salary_thumb.jpg");
  }
 
  private RepositoryManager repositoryManager;
  
  public ArticleContentRepository(RepositoryManager repositoryManager)
  {
    this.repositoryManager = repositoryManager;
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

  public void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", IMAGE_PREFIX + CATEGORY_IMAGE_MAP.get(category));
    }
  }
}
