package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.business.JobResponseModel;
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

  public JobApplyCommand(RequestModel requestModel,
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
    this.requestModel = requestModel;

    jobApplication.setRequestModel(requestModel);
  }
  
  public ResponseModel execute() {
    ResponseModel response = new JobResponseModel();
    try {
      Jobseeker     jobseeker = getJobseeker();
      Job           job = getJob();
      
      jobApplication.setResponseModel(response);
      jobApplication.applyForJob(jobseeker, job);    
    }
    catch(Exception e)
    {
      response.setResult(JobApplicationStatus.ERROR, null);
    }
    return response;
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
