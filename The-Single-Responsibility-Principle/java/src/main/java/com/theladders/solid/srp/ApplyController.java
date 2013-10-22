package com.theladders.solid.srp;

import com.theladders.solid.srp.business.JobApplicationSystem;
import com.theladders.solid.srp.business.Managers;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.job.application.ApplicationFailureException;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.util.IViewProvider;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.SessionData;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;

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
    SessionData sessionData = new SessionData(request);
    
    IViewProvider viewProvider = handle(sessionData, origFileName);
    Result result = viewProvider.getViewResult();
    response.setResult(result);
    return response;
  }

  private IViewProvider handle(SessionData sessionData, String origFileName)
  {
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(managers);

    Jobseeker jobseeker = sessionData.getJobseeker();
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    int jobId = sessionData.getJobId();
    Job job = managers.getJobManager().getJob(jobId);

    ApplyErrorView errorView = new ApplyErrorView();
    IViewProvider viewProvider = errorView;

    try
    {
      if (job == null)
      {
        viewProvider = new InvalidJobView(jobId);
      }
      else
      {
        JobApplicationResult applicationResult = jobApplicationSystem.apply(origFileName, 
                                                                            jobseeker, 
                                                                            job,
                                                                            sessionData);
        if (applicationResult.success())
        {
          if (JobApplicationSystem.isApplicationComingOutsideTheLadders(jobseeker, profile))
          {
            viewProvider = new ResumeCompletionView(job);
          }
          else
          {
            viewProvider = new ApplySuccessView(job);
          }        
        }
        else
        {
          errorView.addMessage("We could not process your application.");
          throw new ApplicationFailureException(applicationResult.toString());
        }        
      }
    }
    catch (Exception e)
    {
      errorView.addMessage(e.getMessage()); // This catches error for Missing resume and adds to the list
      viewProvider = errorView;
    }
    return viewProvider;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = managers.getJobseekerProfileManager().getJobSeekerProfile(jobseeker);
    return profile;
  }
}
