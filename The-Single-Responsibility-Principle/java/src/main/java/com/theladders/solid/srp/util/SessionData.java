package com.theladders.solid.srp.util;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.model.Jobseeker;

public class SessionData
{
  private HttpRequest request;
  private HttpSession session;
  
  public SessionData(HttpRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("SessionData(): missing request.");
    }
    this.session = request.getSession();
    this.request = request;
  }
  
  public String getParameter(String name) {
    String value = request.getParameter(name);
    return value;
  }
  
  public Jobseeker getJobseeker() {
    return session.getJobseeker();
  }
  
  public int getJobId()
  {
    String jobIdString = request.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);
    return jobId;
  }

}
