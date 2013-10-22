package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.business.JobRequestModel;
import com.theladders.solid.srp.business.ResumeFile;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeConstants;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.view.ApplyErrorView;

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
    // We'd like to pass: RequestModel and return a ResponseModel
    // ResponseModel -> Presenter -> returns the proper View
    
    JobApplicationUseCase jobApplication = new JobApplicationUseCase(jobseekerProfileManager,
                                                                     jobApplicationManager,
                                                                     resumeManager,
                                                                     myResumeManager);
    RequestModel model = buildRequestModel(request);
    jobApplication.setRequestModel(model);
    
    boolean showError = false;
    
    ViewProvider viewProvider = null;
    try {
      Job         job = jobManager.getJob(model.getJobId());
      Jobseeker   jobseeker = model.getJobseeker();
      ResumeFile  resumeFile = new ResumeFile(origFileName);
      
      viewProvider = jobApplication.applyForJob(jobseeker, job, resumeFile);      
      if (viewProvider == null) {
        showError = true;
      }
    }
    catch (Exception e)
    {
      showError = true;
    }
    
    if (showError)
    {
      viewProvider = getErrorView("We could not process your application.");
    }
    Result result = viewProvider.getViewResult();
    response.setResult(result);
    return response;
  }
  
  private static ApplyErrorView getErrorView(String message) {
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage(message);
    return errorView;
  }
  
  private static RequestModel buildRequestModel(HttpRequest request) {
    Jobseeker jobseeker = request.getSession().getJobseeker();
    String jobIdString = request.getParameter("jobId");
  
    int jobId = Integer.parseInt(jobIdString);

    String whichResume = request.getParameter(ResumeConstants.WHICH_RESUME);
    boolean hasExistingResume = (ResumeConstants.EXISTING.equals(whichResume));
    
    String makeActiveValue = request.getParameter(ResumeConstants.MAKE_RESUME_ACTIVE);
    boolean makeResumeActive = (ResumeConstants.YES.equals(makeActiveValue));
    
    return new JobRequestModel(jobseeker, jobId, hasExistingResume, makeResumeActive);
  }  
}
