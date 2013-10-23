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

// JobApplyCommand interacts between HTTP and the Use Case: JobApplicationUseCase

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
  
  public ViewProvider execute() {

    ViewProvider viewProvider = null;
    try {
      jobApplication.setRequestModel(requestModel);
      
      Jobseeker   jobseeker = getJobseeker();
      Job         job = getJob();
      
      viewProvider = jobApplication.applyForJob(jobseeker, job);

      if (viewProvider == null) {
        viewProvider = getErrorView();
      }
      return viewProvider;
    }
    catch (Exception e)
    {
      viewProvider = getErrorView();
      return viewProvider;
    }
  }
  
  private static ApplyErrorView getErrorView() {
    String message = "We could not process your application.";
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage(message);
    return errorView;
  }

  private Job getJob()
  {
    return jobManager.getJob(requestModel.getJobId());
  }
  
  private Jobseeker getJobseeker()
  {
    return requestModel.getJobseeker();
  }
}
