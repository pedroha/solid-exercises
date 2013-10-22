package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.job.application.ApplicationFailureException;
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
    JobApplicationEntity jobApplicationSystem = new JobApplicationEntity(jobApplicationManager, 
                                                                         resumeManager,
                                                                         myResumeManager);

    Jobseeker jobseeker = sessionData.getJobseeker();
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = sessionData.getJobId();
    Job job = jobManager.getJob(jobId);

    ApplyErrorView errorView = new ApplyErrorView();
    ViewProvider viewProvider = errorView;

    try
    {
      if (job == null)
      {
        viewProvider = new InvalidJobView(jobId);
      }
      else
      {
        JobApplicationResult applicationResult = jobApplicationSystem.apply(origFileName, 
                                                                            jobseeker, 
                                                                            job,
                                                                            sessionData);
        if (applicationResult.success())
        {
          if (JobApplicationEntity.isApplicationComingOutsideTheLadders(jobseeker, profile))
          {
            viewProvider = new ResumeCompletionView(job);
          }
          else
          {
            viewProvider = new ApplySuccessView(job);
          }        
        }
        else
        {
          throw new ApplicationFailureException(applicationResult.toString());
        }        
      }
    }
    catch (Exception e)
    {
      //errorView.addMessage(e.getMessage()); // This catches error for Missing resume and adds to the list
      errorView.addMessage("We could not process your application.");
      viewProvider = errorView;
    }
    return viewProvider;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return profile;
  }
}
