package com.theladders.solid.srp.business;

import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;

public class Managers
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobManager              jobManager;
  private final JobApplicationManager   jobApplicationManager;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  public Managers(JobseekerProfileManager jobseekerProfileManager,
                  JobManager              jobManager,
                  JobApplicationManager   jobApplicationManager,
                  ResumeManager           resumeManager,
                  MyResumeManager         myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobManager = jobManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public JobseekerProfileManager getJobseekerProfileManager()
  {
    return jobseekerProfileManager;
  }

  public JobManager getJobManager()
  {
    return jobManager;
  }

  public JobApplicationManager getJobApplicationManager()
  {
    return jobApplicationManager;
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
