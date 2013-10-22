package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.SessionData;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobManager              jobManager;
  private final JobApplicationManager   jobApplicationManager;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobManager jobManager,
                         JobApplicationManager jobApplicationManager,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobManager = jobManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    SessionData sessionData = new SessionData(request);
    
    // We'd like to pass: RequestModel and return a ResponseModel
    // ResponseModel -> Presenter -> returns the proper View
    
    JobApplicationUseCase jobApplication = new JobApplicationUseCase(jobseekerProfileManager,
                                                                     jobManager,
                                                                     jobApplicationManager,
                                                                     resumeManager,
                                                                     myResumeManager);
    
    ViewProvider viewProvider = jobApplication.handle(sessionData, origFileName);
    Result result = viewProvider.getViewResult();
    response.setResult(result);
    return response;
  }
}
