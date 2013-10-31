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
  private ResponseModel responseModel;
  private int           jobId;

  public ViewResolver(ResponseModel responseModel, int jobId)
  {
    this.responseModel = responseModel;
    this.jobId = jobId;
  }

  public ViewProvider getViewProvider()
  {
    JobApplyResult result = responseModel.getJobApplyResult();

    JobApplicationStatus status = result.getStatus();

    if (status.equals(JobApplicationStatus.COMPLETE))
    {
      ApplySuccessView success = new ApplySuccessView();
      success.setJob(getJob(result));
      return success;
    }
    else if (status.equals(JobApplicationStatus.NEEDS_PROFILE_COMPLETION))
    {
      ResumeCompletionView resumeCompletion = new ResumeCompletionView();
      resumeCompletion.setJob(getJob(result));
      return resumeCompletion;
    }
    else if (status.equals(JobApplicationStatus.INVALID_JOB))
    {
      InvalidJobView invalid = new InvalidJobView();
      invalid.putData("jobId", jobId);
      return invalid;
    }
    // Else we reach some error!
    // assert (status.equals(JobApplicationStatus.ERROR));

    ApplyErrorView error = new ApplyErrorView();
    error.addMessage("We could not process your application.");
    return error;
  }

  private static Job getJob(JobApplyResult result)
  {
    Map<String, Object> resultData = result.getData();
    return (Job) resultData.get("job");
  }
}
