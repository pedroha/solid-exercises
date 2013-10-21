package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.interfaces.IViewProvider;

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
    JobApplication jobApplication = new JobApplication(this.managers);
    SessionData sessionData = new SessionData(request);
    
    IViewProvider viewProvider = jobApplication.getViewProvider(sessionData, origFileName);
    Result result = viewProvider.getViewResult();
    response.setResult(result);    
    return response;
  }
}
