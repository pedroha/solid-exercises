package com.theladders.solid.srp.web;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.util.JobApplicationStatus;
import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;

public class ViewResolver
{
  private ResponseModel responseModel;
  
  private Map<JobApplicationStatus, Class>viewMap;
  
  public ViewResolver(ResponseModel responseModel) {
    this.responseModel = responseModel;
    this.viewMap = new HashMap<>();
    
    viewMap.put(JobApplicationStatus.INVALID_JOB, InvalidJobView.class);
    viewMap.put(JobApplicationStatus.NEEDS_PROFILE_COMPLETION, ResumeCompletionView.class);
    viewMap.put(JobApplicationStatus.COMPLETE, ApplySuccessView.class);
    viewMap.put(JobApplicationStatus.ERROR, ApplyErrorView.class);
  }

  public ViewProvider getViewProvider()
  {
    JobApplyResult result = responseModel.getJobApplyResult();
    
    JobApplicationStatus status = result.getStatus();
    Map<String, Object> resultData = result.getData();

    ViewProvider viewProvider = null;
    
    if (status.equals(JobApplicationStatus.COMPLETE))
    {
      return new ApplySuccessView(resultData);
    }
    else
    if (status.equals(JobApplicationStatus.NEEDS_PROFILE_COMPLETION))
    {
      return new ResumeCompletionView(resultData);
    }
    else
    if (status.equals(JobApplicationStatus.INVALID_JOB))
    {
      return new InvalidJobView(resultData);
    }
    else
    if (status.equals(JobApplicationStatus.ERROR))
    {
      return new ApplyErrorView(resultData);
    }

    return viewProvider;

//    ViewProvider viewProvider = instantiate(viewMap.get(status));
//    if (viewProvider != null)
//    {
//      String key = responseModel.getKey();
//      if (key != null)
//      {
//        Object data = responseModel.getData();
//        viewProvider.putData(key, data);    
//      }
//      return viewProvider;
//    }
//    return getErrorView();
  }
//  
//  private static ApplyErrorView getErrorView()
//  {
//    String message = "We could not process your application.";
//    ApplyErrorView errorView = new ApplyErrorView();
//    errorView.addMessage(message);
//    return errorView;
//  }
//
//  private static ViewProvider instantiate(Class clazz)
//  {
//    try
//    {
//      return (ViewProvider) clazz.newInstance();
//    }
//    catch (InstantiationException e)
//    {
//      e.printStackTrace();
//    }
//    catch (IllegalAccessException e)
//    {
//      e.printStackTrace();
//    }
//    return null;
//  }
}
