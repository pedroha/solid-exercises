package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;

public class ResumeEntity
{
  private ResumeManager   resumeManager;
  private MyResumeManager myResumeManager;
  
  public ResumeEntity(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager; 
  }
  
  public Resume retrieveExistingResume(Jobseeker jobseeker, boolean hasExistingResume)
  {
    Resume resume = null;
    
    if (hasExistingResume)
    {
      resume = getActiveResume(jobseeker);
    }
    return resume;
  }
  
  public Resume saveNewResume(String newResumeFileName,
                              Jobseeker jobseeker,
                              boolean makeResumeActive)
  {
    if (newResumeFileName == null)
    {
      throw new IllegalArgumentException("JobApplication.saveNewOrRetrieveExistingResume(): newResumeFileName is null");
    }

    Resume resume = resumeManager.saveResume(jobseeker, newResumeFileName);
    if (resume != null && makeResumeActive)
    {
      myResumeManager.saveAsActive(jobseeker, resume);
    }
    return resume;
  }
  
  private Resume getActiveResume(Jobseeker jobseeker)
  {
    return myResumeManager.getActiveResume(jobseeker.getId());
  }  
}
