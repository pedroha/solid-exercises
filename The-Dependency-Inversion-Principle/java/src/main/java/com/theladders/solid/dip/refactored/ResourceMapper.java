package com.theladders.solid.dip.refactored;

import java.util.HashMap;
import java.util.Map;

public class ResourceMapper
{
  private Map<String, String> CATEGORY_IMAGE_MAP = new HashMap<>();
  private String              resourcePrefix;

  public ResourceMapper(String resourcePrefix)
  {
    this.resourcePrefix = resourcePrefix;

    CATEGORY_IMAGE_MAP.put("Interviewing", "interviewing_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Job Search", "job_search_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Networking", "networking_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Personal Branding", "personalBranding_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Resume", "resume_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Salary", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Assessment", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("On the Job", "salary_thumb.jpg");
  }

  public String getImagePath(String category)
  {
    return resourcePrefix + CATEGORY_IMAGE_MAP.get(category);
  }
}
