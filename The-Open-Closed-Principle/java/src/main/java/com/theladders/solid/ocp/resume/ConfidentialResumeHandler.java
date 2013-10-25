package com.theladders.solid.ocp.resume;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.user.User;

public class ConfidentialResumeHandler
{
  private final JobseekerProfileManager            jobSeekerProfileManager;
  private final JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao;

  public ConfidentialResumeHandler(JobseekerProfileManager jobseekerProfileManager,
                                   JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao)
  {
    this.jobSeekerProfileManager = jobseekerProfileManager;
    this.jobseekerConfidentialityProfileDao = jobseekerConfidentialityProfileDao;
  }

  public void makeAllCategoriesNonConfidential(User user)
  {
    JobseekerProfile jsp = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile profile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jsp.getId());

    boolean isChanged = convertAllToNonConfidential(profile);
    if (isChanged)
    {
      generatePermanentConfidentialFiles(user, profile);
    }
  }

  public void makeAllContactInfoNonConfidential(User user)
  {
    JobseekerProfile jsp = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile profile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jsp.getId());

    boolean isChanged = convertContactInfoNonConfidential(profile);
    if (isChanged)
    {
      generatePermanentConfidentialFiles(user, profile);
    }
  }

  private static boolean convertAllToNonConfidential(JobseekerConfidentialityProfile profile)
  {
    boolean isChanged = convertContactInfoNonConfidential(profile);

    for (ConfidentialPhraseCategory category : ConfidentialPhraseCategory.values())
    {
      if (!category.isType(ConfidentialPhraseCategoryType.CONTACT))
      {
        isChanged = profile.resetConfidentialFlagsForCategory(category) || isChanged;
      }
    }
    return isChanged;
  }

  private static boolean convertContactInfoNonConfidential(JobseekerConfidentialityProfile profile)
  {
    boolean isChanged = false;

    for (ConfidentialPhraseCategory category : ConfidentialPhraseCategory.values())
    {
      if (category.isType(ConfidentialPhraseCategoryType.CONTACT))
      {
        isChanged = profile.resetConfidentialFlagsForCategory(category) || isChanged;
      }
    }
    return isChanged;
  }

  @SuppressWarnings("unused")
  private void generatePermanentConfidentialFiles(User user,
                                                  JobseekerConfidentialityProfile profile)
  {
    // stub
  }
}
