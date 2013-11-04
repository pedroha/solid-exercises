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
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;

public class JobApplicationUseCase
{
  private JobseekerProfileManager   jobseekerProfileManager;
  private ResumeInteraction         resumeInteraction;
  private JobApplicationInteraction jobApplicationInteraction;

  private JobApplicationPresenter   presenter;

  public JobApplicationUseCase(JobseekerProfileManager jobseekerProfileManager,
                               JobApplicationManager jobApplicationManager,
                               ResumeManager resumeManager,
                               MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.resumeInteraction = new ResumeInteraction(resumeManager, myResumeManager);
    this.jobApplicationInteraction = new JobApplicationInteraction(jobApplicationManager);
    this.presenter = new JobApplicationPresenter();

  }

  public Result applyForJob(Jobseeker jobseeker,
                            Job job,
                            int jobId,
                            ResumeProfile resumeProfile)
  {
    if (job == null)
    {
      return presenter.invalidJob(jobId);
    }
    Resume resume = handleResumeInteraction(jobseeker, resumeProfile);

    JobApplicationResult applicationResult = jobApplicationInteraction.apply(jobseeker, job, resume);
    if (!applicationResult.success())
    {
      return presenter.error("We could not process your application.");
    }
    if (requiresProfileCompletion(jobseeker))
    {
      return presenter.completeProfile(job);
    }
    return presenter.success(job);
  }

  private Resume handleResumeInteraction(Jobseeker jobseeker,
                                         ResumeProfile resumeProfile)
  {
    Resume resume = resumeInteraction.retrieveExistingResume(jobseeker, resumeProfile.hasExistingResume());
    boolean saveResume = (resume == null);
    if (saveResume)
    {
      ResumeFile resumeFile = resumeProfile.getResumeFile();
      resume = resumeInteraction.saveNewResume(resumeFile, jobseeker, resumeProfile.makeResumeActive());
    }
    return resume;
  }

  private boolean requiresProfileCompletion(Jobseeker jobseeker)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return JobApplicationInteraction.requiresProfileCompletion(jobseeker, profile);
  }
}
