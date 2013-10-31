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
      responseModel.setResult(new JobApplyResult(JobApplicationStatus.INVALID_JOB));
      return;
    }
    Resume resume = handleResumeInteraction(jobseeker, resumeProfile);

    handleJobApplicationInteraction(jobseeker, job, resume);
  }

  private Resume handleResumeInteraction(Jobseeker jobseeker,
                                         ResumeProfile resumeProfile)
  {
    ResumeInteraction resumeInteraction = new ResumeInteraction(resumeManager, myResumeManager);

    Resume resume = resumeInteraction.retrieveExistingResume(jobseeker, resumeProfile.hasExistingResume());
    boolean saveResume = (resume == null);
    if (saveResume)
    {
      ResumeFile resumeFile = resumeProfile.getResumeFile();
      resume = resumeInteraction.saveNewResume(resumeFile, jobseeker, resumeProfile.makeResumeActive());
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
        responseModel.setResult(createJobApplyForJob(JobApplicationStatus.NEEDS_PROFILE_COMPLETION, job));
        return;
      }
      responseModel.setResult(createJobApplyForJob(JobApplicationStatus.COMPLETE, job));
      return;
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    //String message = "We could not process your application.";
    String message = applicationResult.toString(); // ignored later on.
    responseModel.setResult(createJobApplyForError(message));
  }

  private static JobApplyResult createJobApplyForJob(JobApplicationStatus status,
                                                     Job job)
  {
    JobApplyResult result = new JobApplyResult(status);
    result.set("job", job);
    return result;
  }

  private static JobApplyResult createJobApplyForError(String message)
  {
    JobApplyResult result = new JobApplyResult(JobApplicationStatus.ERROR);
    result.set("error", message);
    return result;
  }

  private JobseekerProfile getJobseekerProfile(Jobseeker jobseeker)
  {
    return jobseekerProfileManager.getJobSeekerProfile(jobseeker);
  }
}
