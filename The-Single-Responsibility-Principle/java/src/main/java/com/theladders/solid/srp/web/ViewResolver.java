package com.theladders.solid.srp.web;

import java.util.Map;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.JobApplicationStatus;
import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;

public class ViewResolver
{
  private RequestModel  requestModel;
  private ResponseModel responseModel;
  
  public ViewResolver(RequestModel requestModel, ResponseModel responseModel)
  {
    this.requestModel = requestModel;
    this.responseModel = responseModel;
  }

  public ViewProvider getViewProvider()
  {
    JobApplyResult result = responseModel.getJobApplyResult();
    
    JobApplicationStatus status = result.getStatus();
    Map<String, Object> resultData = result.getData();
    
    if (status.equals(JobApplicationStatus.COMPLETE))
    {
      ApplySuccessView success = new ApplySuccessView();
      success.setJob((Job)resultData.get("job"));
      return success;
    }
    else
    if (status.equals(JobApplicationStatus.NEEDS_PROFILE_COMPLETION))
    {
      ResumeCompletionView resume = new ResumeCompletionView();
      resume.setJob((Job)resultData.get("job"));
      return resume;
    }
    else
    if (status.equals(JobApplicationStatus.INVALID_JOB))
    {
      InvalidJobView invalid =  new InvalidJobView();
      invalid.putData("jobId", requestModel.getJobId());
      return invalid;
    }
    // else if (status.equals(JobApplicationStatus.ERROR))
    ApplyErrorView error =  new ApplyErrorView();
    error.addMessage("We could not process your application.");
    return error;
  }
}
