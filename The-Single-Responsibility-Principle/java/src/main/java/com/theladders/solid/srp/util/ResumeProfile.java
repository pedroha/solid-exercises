package com.theladders.solid.srp.util;

public class ResumeProfile
{
  private boolean    existingResume;
  private boolean    resumeActive;
  private ResumeFile resumeFile;

  public ResumeProfile(ResumeFile resumeFile,
                       boolean existingResume,
                       boolean resumeActive)
  {
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
}
