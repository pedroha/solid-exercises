package com.theladders.solid.srp.business.helpers;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;

public class ManagerAdapter implements ManagerActions
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;

  public ManagerAdapter(JobseekerProfileManager jobseekerProfileManager,
                        JobApplicationManager jobApplicationManager,
                        ResumeManager resumeManager,
                        MyResumeManager myResumeManager)
  {

    this.jobApplicationManager = jobApplicationManager;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public Resume saveResume(Jobseeker jobseeker,
                           String resumeFileName)
  {
    return resumeManager.saveResume(jobseeker, resumeFileName);
  }

  public void saveAsActive(Jobseeker jobseeker, Resume resume)
  {
    myResumeManager.saveAsActive(jobseeker, resume);
  }

  public Resume getActiveResume(Jobseeker jobseeker)
  {
    return myResumeManager.getActiveResume(jobseeker.getId());
  }

  public JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }

  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    return jobApplicationManager.applicationExistsFor(jobseeker, job);
  }

  public void add(SuccessfulApplication application)
  {
    jobApplicationManager.add(application);
  }
}
