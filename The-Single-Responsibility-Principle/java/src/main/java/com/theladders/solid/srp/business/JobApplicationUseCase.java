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
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;

public class JobApplicationUseCase
{
  private JobseekerProfileManager   jobseekerProfileManager;
  private ResumeManager             resumeManager;
  private MyResumeManager           myResumeManager;
  private JobApplicationInteraction jobApplicationInteraction;

  public JobApplicationUseCase(JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
    this.jobApplicationInteraction = new JobApplicationInteraction(jobApplicationManager);
  }

  public JobApplyResult applyForJob(Jobseeker jobseeker,
                                    Job job,
                                    ResumeProfile resumeProfile)
  {
    if (job == null)
    {
      return new JobApplyResult(JobApplicationStatus.INVALID_JOB);
    }
    Resume resume = handleResumeInteraction(jobseeker, resumeProfile);

    JobApplicationResult applicationResult = jobApplicationInteraction.apply(jobseeker, job, resume);
    if (applicationResult.success())
    {
      JobseekerProfile profile = getJobseekerProfile(jobseeker);
      if (JobApplicationInteraction.requiresProfileCompletion(jobseeker, profile))
      {
        return createJobApplyForJob(JobApplicationStatus.NEEDS_PROFILE_COMPLETION, job);
      }
      return createJobApplyForJob(JobApplicationStatus.COMPLETE, job);
    }
    // Don't want to throw an exception to signal a FailedApplication (as in original application)
    // String message = "We could not process your application.";
    String message = applicationResult.toString(); // ignored later on.
    return createJobApplyForError(message);
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
