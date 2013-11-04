package com.theladders.solid.srp.web;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ViewProvider;
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
    RequestModelBuilder builder = new RequestModelBuilder();
    RequestModel        requestModel = builder.buildRequestModel(request, origFileName);
    ResponseModel       responseModel = new JobResponseModel();

    JobApplyCommand jobApply = commandFactory.createJobApply(requestModel);

    JobApplyResult result = jobApply.execute();
    responseModel.setResult(result);

    response.setResult(getResult(requestModel, responseModel));
    return response;
  }
  
  private static Result getResult(RequestModel requestModel, ResponseModel responseModel) 
  {
    ViewResolver viewResolver = new ViewResolver(responseModel, requestModel.getJobId());
    ViewProvider viewProvider = viewResolver.getViewProvider();
    return viewProvider.getViewResult();
  }
}
