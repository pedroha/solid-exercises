package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;

public class ResumeBusiness
{
  private Managers managers;
  
  public ResumeBusiness(Managers managers)
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
  
  private boolean makeResumeActive(Resume resume, SessionData sessionData) {
    String makeActiveValue = sessionData.getParameter("makeResumeActive");
    boolean makeActive = (resume != null && "yes".equals(makeActiveValue));
    return makeActive;
  }
  
  private Resume getActiveResume(Jobseeker jobseeker)
  {
    Resume resume = managers.getMyResumeManager().getActiveResume(jobseeker.getId());
    return resume;
  }
  
  private boolean activeResumeExists(SessionData sessionData)
  {
    boolean exists = ("existing".equals(sessionData.getParameter("whichResume")));
    return exists;
  }
}
