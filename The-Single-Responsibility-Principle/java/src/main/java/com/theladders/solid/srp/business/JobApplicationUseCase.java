package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;


public class JobApplicationUseCase
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;
  private RequestModel            requestModel;

  public JobApplicationUseCase(JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager) {
  
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public void setRequestModel(RequestModel model)
  {
    this.requestModel = model;
  }
  
  public ViewProvider applyForJob(Jobseeker jobseeker, Job job)
  {
    JobApplicationInteraction jobApplicationInteraction = new JobApplicationInteraction(jobApplicationManager);
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    
    ViewProvider viewProvider = null;

    if (job == null)
    {
      viewProvider = new InvalidJobView(requestModel.getJobId());
      return viewProvider;
    }   
    ResumeInteraction resumeInteraction = new ResumeInteraction(resumeManager, myResumeManager);
    
    Resume resume = resumeInteraction.retrieveExistingResume(jobseeker, requestModel.hasExistingResume());
    if (resume == null) {
      ResumeFile resumeFile = requestModel.getResumeFile();
      String origFileName = resumeFile.getFileName();
      resume = resumeInteraction.saveNewResume(origFileName, jobseeker, requestModel.makeResumeActive());
    }

    JobApplicationResult applicationResult = jobApplicationInteraction.apply(jobseeker, job, resume);
    if (applicationResult.success())
    {
      if (JobApplicationInteraction.requiresProfileCompletion(jobseeker, profile))
      {
        viewProvider = new ResumeCompletionView(job);
        return viewProvider;
      }
      viewProvider = new ApplySuccessView(job);
      return viewProvider;        
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    return getErrorView();    
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
  
  public static ApplyErrorView getErrorView() {
    String message = "We could not process your application.";
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage(message);
    return errorView;
  }  
}
