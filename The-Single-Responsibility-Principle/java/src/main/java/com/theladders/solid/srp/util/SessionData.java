package com.theladders.solid.srp.util;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;

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
  
  public Jobseeker getJobseeker() {
    return session.getJobseeker();
  }
  
  public int getJobId()
  {
    String jobIdString = request.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);
    return jobId;
  }
  
  public boolean makeResumeActive(Resume resume)
  {
    String makeActiveValue = getParameter(IResume.MAKE_RESUME_ACTIVE);
    boolean makeActive = (resume != null && IResume.YES.equals(makeActiveValue));
    return makeActive;
  }
    
  public boolean activeResumeExists()
  {
    String whichResume = getParameter(IResume.WHICH_RESUME);
    boolean exists = (IResume.EXISTING.equals(whichResume));
    return exists;
  }

  private String getParameter(String name) {
    String value = request.getParameter(name);
    return value;
  }
}
