package com.theladders.solid.srp.business.helpers;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;

public interface ManagerActions extends ResumeActions
{
  JobseekerProfile getJobSeekerProfile(Jobseeker jobseeker);
  boolean applicationExistsFor(Jobseeker jobseeker, Job job);
  void add(SuccessfulApplication application);
}
