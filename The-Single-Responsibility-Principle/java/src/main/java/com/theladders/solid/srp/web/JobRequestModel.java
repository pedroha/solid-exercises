package com.theladders.solid.srp.web;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.util.RequestModel;
import com.theladders.solid.srp.util.ResumeFile;

public class JobRequestModel implements RequestModel
{
  private int           jobId;
  private Jobseeker     jobseeker;
  private boolean       existingResume;
  private boolean       resumeActive;
  private ResumeFile    resumeFile;
  
  public JobRequestModel(Jobseeker jobseeker,
                         int jobId,
                         ResumeFile resumeFile,
                         boolean existingResume,
                         boolean resumeActive)
  {
    this.jobseeker = jobseeker;
    this.jobId = jobId;
    this.resumeFile = resumeFile;
    this.existingResume = existingResume;
    this.resumeActive = resumeActive;
  }
  
  public ResumeFile getResumeFile()
  {
    return resumeFile;
  }
  
  public boolean hasExistingResume()
  {
    return existingResume;
  }

  public boolean makeResumeActive()
  {
    return resumeActive;
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
