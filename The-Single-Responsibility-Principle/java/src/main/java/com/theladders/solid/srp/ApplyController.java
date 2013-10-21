package com.theladders.solid.srp;

import com.theladders.solid.srp.business.JobApplicationSystem;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.util.IViewProvider;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.SessionData;

public class ApplyController
{
  private Managers managers;

  public ApplyController(Managers managers)
  {
    this.managers = managers;
  }
  
  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    JobApplicationSystem jobApplication = new JobApplicationSystem(this.managers);
    SessionData sessionData = new SessionData(request);
    
    IViewProvider viewProvider = jobApplication.getViewProvider(sessionData, origFileName);
    Result result = viewProvider.getViewResult();
    response.setResult(result);    
    return response;
  }
}
