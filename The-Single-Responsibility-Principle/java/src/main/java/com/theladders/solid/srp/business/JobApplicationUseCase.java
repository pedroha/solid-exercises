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
import com.theladders.solid.srp.util.JobApplicationStatus;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ResumeFile;

public class JobApplicationUseCase
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;
  private RequestModel            requestModel;
  private ResponseModel           responseModel;
  
  public JobApplicationUseCase(RequestModel requestModel,
                               ResponseModel responseModel,
                               JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.requestModel = requestModel;
    this.responseModel = responseModel;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public void applyForJob(Jobseeker jobseeker, Job job)
  {
    if (job == null)
    {
      responseModel.setResult(JobApplicationStatus.INVALID_JOB, "jobId", requestModel.getJobId());
      return;
    }
    Resume resume = handleResumeInteraction(jobseeker);

    handleJobApplicationInteraction(jobseeker, job, resume);
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

  private void handleJobApplicationInteraction(Jobseeker jobseeker,
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
        responseModel.setResult(JobApplicationStatus.NEEDS_PROFILE_COMPLETION, "job", job);
        return;
      }
      responseModel.setResult(JobApplicationStatus.COMPLETE, "job", job);
      return;
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    // String message = applicationResult.toString();
    String message = "We could not process your application.";
    responseModel.setResult(JobApplicationStatus.ERROR, "errorMessage", message);
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
}
