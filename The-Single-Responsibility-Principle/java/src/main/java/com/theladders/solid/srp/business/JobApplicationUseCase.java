package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.SessionData;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;

public class JobApplicationUseCase
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobManager              jobManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;

  public JobApplicationUseCase(JobseekerProfileManager jobseekerProfileManager,
                                  JobManager jobManager,
                                  JobApplicationManager jobApplicationManager,
                                  ResumeManager resumeManager,
                                  MyResumeManager myResumeManager) {
  
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobManager = jobManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
 
  public ViewProvider handle(SessionData sessionData, String origFileName)
  {
    try {
      return handle2(sessionData, origFileName);      
    }
    catch (Exception e) {
      ApplyErrorView errorView = new ApplyErrorView();
      errorView.addMessage("We could not process your application.");
      return errorView;
    }
  }
   
  private ViewProvider handle2(SessionData sessionData, String origFileName)
  {
    JobApplicationEntity jobApplicationSystem = new JobApplicationEntity(jobApplicationManager);
                                                                         
    Jobseeker jobseeker = sessionData.getJobseeker();
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = sessionData.getJobId();
    Job job = jobManager.getJob(jobId);

    ViewProvider viewProvider = null;

    if (job == null)
    {
      viewProvider = new InvalidJobView(jobId);
      return viewProvider;
    }

    ResumeEntity resumeSystem = new ResumeEntity(resumeManager, myResumeManager);
    Resume resume = resumeSystem.retrieveExistingResume(jobseeker, sessionData);
    if (resume == null) {
      resume = resumeSystem.saveNewResume(origFileName,jobseeker, sessionData);
    }

    JobApplicationResult applicationResult = jobApplicationSystem.apply(resume, jobseeker, job);
    if (applicationResult.success())
    {
      if (JobApplicationEntity.requiresProfileCompletion(jobseeker, profile))
      {
        viewProvider = new ResumeCompletionView(job);
        return viewProvider;
      }
      viewProvider = new ApplySuccessView(job);
      return viewProvider;        
    }
    // Removing:
    // throw new ApplicationFailureException(applicationResult.toString());
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage("We could not process your application.");
    return errorView;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
}
