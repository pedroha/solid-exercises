package com.theladders.solid.srp.web;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.Result;

public class ApplyController
{
  private CommandFactory commandFactory;
  
  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobManager jobManager,
                         JobApplicationManager jobApplicationManager,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.commandFactory = new CommandFactory(jobseekerProfileManager,
                                             jobManager,
                                             jobApplicationManager,
                                             resumeManager,
                                             myResumeManager);
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    JobRequestModelBuilder builder = new JobRequestModelBuilder();
    JobRequestModel     requestModel = builder.buildRequestModel(request, origFileName);

    JobApplyCommand     jobApply = commandFactory.createJobApply(requestModel);
    Result              result = jobApply.execute();

    response.setResult(result);
    return response;
  }
}
