package com.theladders.solid.srp.business;

import com.theladders.solid.srp.Managers;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.util.SessionData;

public class ResumeHandler
{
  private Managers managers;
  
  public ResumeHandler(Managers managers)
  {
    this.managers = managers;
  }
  
  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 SessionData sessionData)
  {
    Resume resume = null;

    if (activeResumeExists(sessionData))
    {
      resume = getActiveResume(jobseeker);
    }
    else
    {
      if (newResumeFileName == null) {
        throw new IllegalArgumentException("JobApplication.saveNewOrRetrieveExistingResume(): newResumeFileName is null");
      }
      resume = saveResume(jobseeker, newResumeFileName);

      if (makeResumeActive(resume, sessionData))
      {
        saveResumeAsActive(jobseeker, resume);
      }
    }

    return resume;
  }
  
  private Resume saveResume(Jobseeker jobseeker, String newResumeFileName) {
    Resume resume = managers.getResumeManager().saveResume(jobseeker, newResumeFileName);
    return resume;
  }
  
  private void saveResumeAsActive(Jobseeker jobseeker, Resume resume)
  {
    managers.getMyResumeManager().saveAsActive(jobseeker, resume);
  }
  
  private static boolean makeResumeActive(Resume resume, SessionData sessionData) {
    String makeActiveValue = sessionData.getParameter("makeResumeActive");
    boolean makeActive = (resume != null && "yes".equals(makeActiveValue));
    return makeActive;
  }
  
  private Resume getActiveResume(Jobseeker jobseeker)
  {
    Resume resume = managers.getMyResumeManager().getActiveResume(jobseeker.getId());
    return resume;
  }
  
  private static boolean activeResumeExists(SessionData sessionData)
  {
    boolean exists = ("existing".equals(sessionData.getParameter("whichResume")));
    return exists;
  }
}
