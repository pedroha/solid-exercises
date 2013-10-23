package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;

// JobApplyCommand translates between HTTP to domain language

public class JobApplyCommand
{
  private JobApplicationUseCase jobApplication;
  private JobManager      jobManager;
  private RequestModel          requestModel;

  public JobApplyCommand(RequestModel model,
                         JobseekerProfileManager jobseekerProfileManager,
                         JobManager jobManager,
                         JobApplicationManager jobApplicationManager,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplication =  new JobApplicationUseCase(jobseekerProfileManager,
                                                     jobApplicationManager,
                                                     resumeManager,
                                                     myResumeManager);
    this.jobManager = jobManager;
    this.requestModel = model;
  }
  
  private Job getJob()
  {
    return jobManager.getJob(requestModel.getJobId());
  }
  
  public ViewProvider execute() {
    
    jobApplication.setRequestModel(requestModel);
    
    boolean showError = false;
    
    ViewProvider viewProvider = null;
    try {
      Jobseeker   jobseeker = requestModel.getJobseeker();
      Job         job = getJob();
      
      viewProvider = jobApplication.applyForJob(jobseeker, job);      
      if (viewProvider == null) {
        showError = true;
      }
    }
    catch (Exception e)
    {
      showError = true;
    }
    
    if (showError)
    {
      viewProvider = getErrorView("We could not process your application.");
    }
    return viewProvider;
  }
  
  private static ApplyErrorView getErrorView(String message) {
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage(message);
    return errorView;
  }  
}
