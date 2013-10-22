package com.theladders.solid.srp.web;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.business.ResumeFile;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.ResumeConstants;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.SessionData;
import com.theladders.solid.srp.view.ApplyErrorView;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobManager              jobManager;
  private final JobApplicationManager   jobApplicationManager;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  private SessionData sessionData;

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
    this.sessionData = new SessionData(request);
    
    // We'd like to pass: RequestModel and return a ResponseModel
    // ResponseModel -> Presenter -> returns the proper View
    
    JobApplicationUseCase jobApplication = new JobApplicationUseCase(jobseekerProfileManager,
                                                                     jobApplicationManager,
                                                                     resumeManager,
                                                                     myResumeManager);
    ResumeFile resumeFile = new ResumeFile(origFileName);
    
    ViewProvider viewProvider = null;    
    try {
      Jobseeker jobseeker = request.getSession().getJobseeker();
      String jobIdString = request.getParameter("jobId");
      int jobId = Integer.parseInt(jobIdString);

      Job job = jobManager.getJob(jobId);
      
      viewProvider = jobApplication.applyForJob(jobseeker, job, resumeFile, jobId, hasExistingResume(), makeResumeActive());
      if (viewProvider == null) {
        viewProvider = getErrorView("We could not process your application.");
      }
    }
    catch (Exception e)
    {
      viewProvider = getErrorView("We could not process your application.");
    }
    Result result = viewProvider.getViewResult();
    response.setResult(result);
    return response;
  }
  
  private static ApplyErrorView getErrorView(String message) {
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage("We could not process your application.");
    return errorView;
  }
  
  private boolean hasExistingResume()
  {
    String whichResume = sessionData.getParameter(ResumeConstants.WHICH_RESUME);
    return (ResumeConstants.EXISTING.equals(whichResume));
  }

  private boolean makeResumeActive()
  {
    String makeActiveValue = sessionData.getParameter(ResumeConstants.MAKE_RESUME_ACTIVE);
    return (ResumeConstants.YES.equals(makeActiveValue));
  }
}
