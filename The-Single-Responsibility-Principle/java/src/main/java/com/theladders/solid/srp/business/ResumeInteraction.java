package com.theladders.solid.srp.business;

import com.theladders.solid.srp.business.helpers.ResumeActions;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.util.ResumeFile;

public class ResumeInteraction
{
  private ResumeActions resumeActions;
  
  public ResumeInteraction(ResumeActions resumeActions)
  {
    this.resumeActions = resumeActions;
  }
  
  public Resume retrieveExistingResume(Jobseeker jobseeker, boolean hasExistingResume)
  {
    if (hasExistingResume)
    {
      return resumeActions.getActiveResume(jobseeker);
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
    Resume resume = resumeActions.saveResume(jobseeker, newResumeFileName);
    if (resume != null && makeResumeActive)
    {
      resumeActions.saveAsActive(jobseeker, resume);
    }
    return resume;
  }
}
