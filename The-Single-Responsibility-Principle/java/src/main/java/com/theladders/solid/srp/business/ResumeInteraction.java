package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.ResumeFile;

public class ResumeInteraction
{
  private ResumeManager   resumeManager;
  private MyResumeManager myResumeManager;
  
  public ResumeInteraction(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager; 
  }
  
  public Resume retrieveExistingResume(Jobseeker jobseeker, boolean hasExistingResume)
  {
    if (hasExistingResume)
    {
      return getActiveResume(jobseeker);
    }
    return null;
  }
  
  public Resume saveNewResume(ResumeFile resumeFile,
                              Jobseeker jobseeker,
                              boolean makeResumeActive)
  {
    String newResumeFileName = resumeFile.getFileName();
    if (newResumeFileName == null)
    {
      return null;
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
