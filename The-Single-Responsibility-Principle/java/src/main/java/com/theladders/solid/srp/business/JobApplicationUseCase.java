package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.JobApplicationResult;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ViewProvider;
import com.theladders.solid.srp.view.ApplyErrorView;
import com.theladders.solid.srp.view.ApplySuccessView;
import com.theladders.solid.srp.view.InvalidJobView;
import com.theladders.solid.srp.view.ResumeCompletionView;


public class JobApplicationUseCase
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;
  private RequestModel            requestModel;

  public JobApplicationUseCase(JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public void setRequestModel(RequestModel model)
  {
    this.requestModel = model;
  }
  
  public ViewProvider applyForJob(Jobseeker jobseeker, Job job)
  {
    if (job == null)
    {
      return new InvalidJobView(requestModel.getJobId());
    }
    Resume resume = handleResumeInteraction(jobseeker);

    return handleJobApplicationInteraction(jobseeker, job, resume);
  }

  private ViewProvider handleJobApplicationInteraction(Jobseeker jobseeker,
                                                       Job job,
                                                       Resume resume)
  {
    JobApplicationInteraction jobApplicationInteraction = new JobApplicationInteraction(jobApplicationManager);
    JobApplicationResult applicationResult = jobApplicationInteraction.apply(jobseeker, job, resume);
    if (applicationResult.success())
    {
      JobseekerProfile profile = getJobseekerProfile(jobseeker);
      if (JobApplicationInteraction.requiresProfileCompletion(jobseeker, profile))
      {
        return new ResumeCompletionView(job);
      }
      return new ApplySuccessView(job);   
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    return getErrorView();
  }

  private Resume handleResumeInteraction(Jobseeker jobseeker)
  {
    ResumeInteraction resumeInteraction = new ResumeInteraction(resumeManager, myResumeManager);
    
    Resume resume = resumeInteraction.retrieveExistingResume(jobseeker, requestModel.hasExistingResume());
    boolean saveResume =  (resume == null);
    if (saveResume)
    {
      ResumeFile resumeFile = requestModel.getResumeFile();
      String origFileName = resumeFile.getFileName();
      resume = resumeInteraction.saveNewResume(origFileName, jobseeker, requestModel.makeResumeActive());
    }
    return resume;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
  
  public static ApplyErrorView getErrorView()
  {
    String message = "We could not process your application.";
    ApplyErrorView errorView = new ApplyErrorView();
    errorView.addMessage(message);
    return errorView;
  }  
}
