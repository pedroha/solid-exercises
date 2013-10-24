package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.JobApplicationStatus;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;

// JobApplyCommand interacts between HTTP and the Use Case: JobApplicationUseCase

public class JobApplyCommand
{
  private JobApplicationUseCase jobApplication;
  private JobManager            jobManager;
  private RequestModel          requestModel;
  private ResponseModel         responseModel;

  public JobApplyCommand(RequestModel requestModel,
                         JobseekerProfileManager jobseekerProfileManager,
                         JobManager jobManager,
                         JobApplicationManager jobApplicationManager,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.responseModel = new JobResponseModel();

    this.jobApplication =  new JobApplicationUseCase(requestModel,
                                                     responseModel,
                                                     jobseekerProfileManager,
                                                     jobApplicationManager,
                                                     resumeManager,
                                                     myResumeManager);
    this.jobManager = jobManager;
    this.requestModel = requestModel;
  }
  
  public ResponseModel execute() {
    try {
      Jobseeker     jobseeker = getJobseeker();
      Job           job = getJob();
      
      jobApplication.applyForJob(jobseeker, job);    
    }
    catch(Exception e)
    {
      String message = "We could not process your application.";
      responseModel.setResult(JobApplicationStatus.ERROR, "errorMessage", message);
    }
    return responseModel;
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
