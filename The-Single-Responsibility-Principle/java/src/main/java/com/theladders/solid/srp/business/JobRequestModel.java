package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.util.RequestModel;

public class JobRequestModel implements RequestModel
{
  private int jobId;
  private Jobseeker jobseeker;
  private boolean existingResume;
  private boolean resumeActive;
  
  public JobRequestModel(Jobseeker jobseeker,
                         int jobId,
                         boolean existingResume,
                         boolean resumeActive)
  {
    this.jobseeker = jobseeker;
    this.jobId = jobId;
    this.existingResume = existingResume;
    this.resumeActive = resumeActive;
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
