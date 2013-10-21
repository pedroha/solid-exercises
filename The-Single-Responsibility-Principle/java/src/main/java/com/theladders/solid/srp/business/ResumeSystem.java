package com.theladders.solid.srp.business;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.SessionData;

public class ResumeSystem
{
  private ResumeManager   resumeManager;
  private MyResumeManager myResumeManager;
  
  public ResumeSystem(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager; 
  }
  
  public Resume retrieveExistingResume(Jobseeker jobseeker, SessionData sessionData)
  {
    Resume resume = null;
    
    if (sessionData.activeResumeExists())
    {
      resume = getActiveResume(jobseeker);
    }
    return resume;
  }
  
  public Resume saveNewResume(String newResumeFileName,
                                     Jobseeker jobseeker,
                                     SessionData sessionData)
  {
    if (newResumeFileName == null)
    {
      throw new IllegalArgumentException("JobApplication.saveNewOrRetrieveExistingResume(): newResumeFileName is null");
    }
    Resume resume = saveResume(jobseeker, newResumeFileName);
    
    if (sessionData.makeResumeActive(resume))
    {
      saveResumeAsActive(jobseeker, resume);
    }

    return resume;
  }
  
  private Resume saveResume(Jobseeker jobseeker, String newResumeFileName)
  {
    Resume resume = resumeManager.saveResume(jobseeker, newResumeFileName);
    return resume;
  }
  
  private void saveResumeAsActive(Jobseeker jobseeker, Resume resume)
  {
    myResumeManager.saveAsActive(jobseeker, resume);
  }
  
  private Resume getActiveResume(Jobseeker jobseeker)
  {
    Resume resume = myResumeManager.getActiveResume(jobseeker.getId());
    return resume;
  }  
}
