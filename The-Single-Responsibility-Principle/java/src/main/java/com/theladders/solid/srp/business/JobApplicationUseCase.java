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
import com.theladders.solid.srp.util.JobApplyResult;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResponseModel;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;

public class JobApplicationUseCase
{
  private JobseekerProfileManager jobseekerProfileManager;
  private JobApplicationManager   jobApplicationManager;
  private ResumeManager           resumeManager;
  private MyResumeManager         myResumeManager;
  private ResponseModel           responseModel;
  
  public JobApplicationUseCase(ResponseModel responseModel,
                               JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.responseModel = responseModel;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationManager = jobApplicationManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public void applyForJob(Jobseeker jobseeker,
                          Job job,
                          ResumeProfile resumeProfile)
  {
    if (job == null)
    {
      JobApplyResult result = new JobApplyResult(JobApplicationStatus.INVALID_JOB);
      responseModel.setResult(result);
      return;
    }
    Resume resume = handleResumeInteraction(jobseeker, resumeProfile);

    handleJobApplicationInteraction(jobseeker, job, resume);
  }

  private Resume handleResumeInteraction(Jobseeker jobseeker, ResumeProfile resumeProfile)
  {
    ResumeInteraction resumeInteraction = new ResumeInteraction(resumeManager, myResumeManager);
    
    Resume resume = resumeInteraction.retrieveExistingResume(jobseeker, resumeProfile.hasExistingResume());
    boolean saveResume = (resume == null);
    if (saveResume)
    {
      ResumeFile resumeFile = resumeProfile.getResumeFile();
      String origFileName = resumeFile.getFileName();
      resume = resumeInteraction.saveNewResume(origFileName, jobseeker, resumeProfile.makeResumeActive());
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
        JobApplyResult result = new JobApplyResult(JobApplicationStatus.NEEDS_PROFILE_COMPLETION);
        result.set("job", job);
        responseModel.setResult(result);
        return;
      }
      JobApplyResult result = new JobApplyResult(JobApplicationStatus.COMPLETE);
      result.set("job", job);
      responseModel.setResult(result);
      return;
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    // String message = applicationResult.toString();
    String message = "We could not process your application.";

    JobApplyResult result = new JobApplyResult(JobApplicationStatus.ERROR);
    result.set("errorMessage", message);
    responseModel.setResult(result);
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
}
