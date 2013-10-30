package com.theladders.solid.ocp;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategoryExtendedWithFavorites;
import com.theladders.solid.ocp.resume.ConfidentialResumeHandler;
import com.theladders.solid.ocp.resume.JobseekerProfileManager;
import com.theladders.solid.ocp.resume.ResumeConfidentialityManager;
import com.theladders.solid.ocp.user.User;
import com.theladders.solid.ocp.util.Category;

public class App
{
  public static void main(String[] args)
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager();
    JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao = new JobseekerConfidentialityProfileDao();
    ConfidentialResumeHandler confidentialResumeHandler = new ConfidentialResumeHandler(jobseekerProfileManager, jobseekerConfidentialityProfileDao);
    ResumeConfidentialityManager resumeConfidentialityManager = new ResumeConfidentialityManager(confidentialResumeHandler);
    
    // Without reference to "static", the others are not visible!
    // ConfidentialPhraseCategory cat = ConfidentialPhraseCategoryExtendedWithFavorites.FavouriteFood;

    int id = 1;
    User user = new User(id);

    resumeConfidentialityManager.makeAllContactInfoNonConfidential(user);
    resumeConfidentialityManager.makeAllCategoriesNonConfidential(user);
  }
}
