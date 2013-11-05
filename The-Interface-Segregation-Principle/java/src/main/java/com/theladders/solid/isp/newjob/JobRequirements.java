package com.theladders.solid.isp.newjob;

import java.util.Collection;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;

public interface JobRequirements
{
  List<Discipline> getDisciplines();

  /**
   * Returns an object that represents the number of years of experience
   * that are required for this job.
   * @return experience object
   */
  Experience getExperience();
  
  Collection<JobFunction> getJobFunctions();

  /**
   * Is this job a JobReq? JobReqs are jobs entered into our site directly by recruiters.
   * 
   * @return true if job is a JobReq, false otherwise.
   */
  boolean isJobReq();
}
