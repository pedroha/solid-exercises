package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobRequestModel;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeFile;

public class RequestModelBuilder
{
  public RequestModelBuilder()
  {
  }
  
  public RequestModel buildRequestModel(HttpRequest request, String origFileName) {
    ResumeFile resumeFile = new ResumeFile(origFileName);
    
    Jobseeker jobseeker = request.getSession().getJobseeker();

    boolean hasExistingResume = getWhichResume(request);
    boolean makeResumeActive = getMakeActive(request);
    
    int jobId = getJobId(request);
    // Job job = jobManager.getJob(jobId);
    
    return new JobRequestModel(jobseeker, jobId, resumeFile, hasExistingResume, makeResumeActive);
  }
  
  private int getJobId(HttpRequest request)
  {
    String jobIdString = request.getParameter("jobId");
    return Integer.parseInt(jobIdString);    
  }
  
  private boolean getWhichResume(HttpRequest request)
  {
    String whichResume = request.getParameter(ResumeConstants.WHICH_RESUME);
    return (ResumeConstants.EXISTING.equals(whichResume));
  }
  
  private boolean getMakeActive(HttpRequest request)
  {
    String makeActive = request.getParameter(ResumeConstants.MAKE_RESUME_ACTIVE);
    return (ResumeConstants.YES.equals(makeActive));
  }
}
