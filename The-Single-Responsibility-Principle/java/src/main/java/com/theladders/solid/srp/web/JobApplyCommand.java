package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.Command;
import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ResumeProfile;

// JobApplyCommand interacts between HTTP and the Use Case: JobApplicationUseCase

public class JobApplyCommand implements Command
{
  private JobApplicationUseCase jobApplication;
  private JobManager            jobManager;
  private RequestModel          requestModel;
  private ResponseModel         responseModel;

  public JobApplyCommand(RequestModel requestModel,
                         ResponseModel responseModel,
                         JobseekerProfileManager jobseekerProfileManager,
                         JobManager jobManager,
                         JobApplicationManager jobApplicationManager,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplication = new JobApplicationUseCase(jobseekerProfileManager,
                                                    jobApplicationManager,
                                                    resumeManager,
                                                    myResumeManager);
    this.jobManager = jobManager;
    this.requestModel = requestModel;
    this.responseModel = responseModel;
  }

  public void execute()
  {
      Jobseeker         jobseeker = getJobseeker();
      Job               job = getJob();
      ResumeProfile     resumeProfile = getResumeProfile();

      JobApplyResult result = jobApplication.applyForJob(jobseeker, job, resumeProfile);
      responseModel.setResult(result);
  }

  private ResumeProfile getResumeProfile()
  {
    return requestModel.getResumeProfile();
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
