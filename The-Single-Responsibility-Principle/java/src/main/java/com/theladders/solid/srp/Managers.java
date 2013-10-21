package com.theladders.solid.srp;

import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class Managers
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  public Managers(JobseekerProfileManager jobseekerProfileManager,
                  JobSearchService        jobSearchService,
                  JobApplicationSystem    jobApplicationSystem,
                  ResumeManager           resumeManager,
                  MyResumeManager         myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
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

  public JobApplicationSystem getJobApplicationSystem()
  {
    return jobApplicationSystem;
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
