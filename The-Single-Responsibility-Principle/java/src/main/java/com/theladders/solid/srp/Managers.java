package com.theladders.solid.srp;

import com.theladders.solid.srp.services.JobApplicationService;
import com.theladders.solid.srp.services.JobSearchService;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;

public class Managers
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationService   jobApplicationService;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  public Managers(JobseekerProfileManager jobseekerProfileManager,
                  JobSearchService        jobSearchService,
                  JobApplicationService   jobApplicationService,
                  ResumeManager           resumeManager,
                  MyResumeManager         myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationService = jobApplicationService;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public JobseekerProfileManager getJobseekerProfileManager()
  {
    return jobseekerProfileManager;
  }

  public JobSearchService getJobSearchService()
  {
    return jobSearchService;
  }

  public JobApplicationService getJobApplicationService()
  {
    return jobApplicationService;
  }

  public ResumeManager getResumeManager()
  {
    return resumeManager;
  }

  public MyResumeManager getMyResumeManager()
  {
    return myResumeManager;
  }
}
