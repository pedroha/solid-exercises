package com.theladders.solid.srp.web;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;

public class JobRequestModel implements RequestModel
{
  private int           jobId;
  private Jobseeker     jobseeker;
  private ResumeProfile resumeProfile;

  public JobRequestModel(Jobseeker jobseeker,
                         int jobId,
                         ResumeFile resumeFile,
                         boolean existingResume,
                         boolean resumeActive)
  {
    this.jobseeker = jobseeker;
    this.jobId = jobId;

    this.resumeProfile = new ResumeProfile(resumeFile, existingResume, resumeActive);
  }

  public ResumeProfile getResumeProfile()
  {
    return resumeProfile;
  }

  public Jobseeker getJobseeker()
  {
    return jobseeker;
  }

  public int getJobId()
  {
    return jobId;
  }
}
