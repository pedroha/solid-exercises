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
import com.theladders.solid.srp.util.ViewProvider;
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
                               MyResumeManager myResumeManager) {
  
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public void setRequestModel(RequestModel model)
  {
    this.requestModel = model;
  }
  
  public ViewProvider applyForJob(Jobseeker jobseeker,
                                  Job job,
                                  ResumeFile resumeFile)
  {
    JobApplicationEntity jobApplicationEntity = new JobApplicationEntity(jobApplicationManager);
    JobseekerProfile profile = getJobseekerProfile(jobseeker);
    
    ViewProvider viewProvider = null;

    if (job == null)
    {
      viewProvider = new InvalidJobView(requestModel.getJobId());
      return viewProvider;
    }   
    ResumeEntity resumeEntity = new ResumeEntity(resumeManager, myResumeManager);
    
    Resume resume = resumeEntity.retrieveExistingResume(jobseeker, requestModel.hasExistingResume());
    if (resume == null) {
      String origFileName = resumeFile.getFileName();
      resume = resumeEntity.saveNewResume(origFileName, jobseeker, requestModel.makeResumeActive());
    }

    JobApplicationResult applicationResult = jobApplicationEntity.apply(jobseeker, job, resume);
    if (applicationResult.success())
    {
      if (JobApplicationEntity.requiresProfileCompletion(jobseeker, profile))
      {
        viewProvider = new ResumeCompletionView(job);
        return viewProvider;
      }
      viewProvider = new ApplySuccessView(job);
      return viewProvider;        
    }
    return null;    
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
}
