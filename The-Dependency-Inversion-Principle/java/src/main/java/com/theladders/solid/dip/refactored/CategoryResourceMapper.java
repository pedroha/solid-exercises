package com.theladders.solid.dip.refactored;

import java.util.HashMap;
import java.util.Map;

public class CategoryResourceMapper implements ResourceMapper
{
  private String              IMAGE_PREFIX       = "http://somecdnprodiver.com/static/images/careerAdvice/";
  private Map<String, String> CATEGORY_IMAGE_MAP = new HashMap<>();

  public CategoryResourceMapper()
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

  public String getResourcePathByCategory(String category)
  {
    return IMAGE_PREFIX + CATEGORY_IMAGE_MAP.get(category);
  }
}
