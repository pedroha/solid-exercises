package com.theladders.solid.srp.web;

import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.RequestModel;

public class CommandFactory
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobManager              jobManager;
  private final JobApplicationManager   jobApplicationManager;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public CommandFactory(JobseekerProfileManager jobseekerProfileManager,
                        JobManager jobManager,
                        JobApplicationManager jobApplicationManager,
                        ResumeManager resumeManager,
                        MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobManager = jobManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public JobApplyCommand createJobApply(RequestModel requestModel)
  {
    
    JobApplyCommand jobApply = new JobApplyCommand(requestModel,
                                                   jobseekerProfileManager,
                                                   jobManager,
                                                   jobApplicationManager,
                                                   resumeManager,
                                                   myResumeManager);
    return jobApply;
  }
}
