package com.theladders.solid.srp.business.helpers;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;

public interface ResumeActions
{
  Resume saveResume(Jobseeker jobseeker, String resumeFileName);
  void saveAsActive(Jobseeker jobseeker, Resume resume);
  Resume getActiveResume(Jobseeker jobseeker);
}
