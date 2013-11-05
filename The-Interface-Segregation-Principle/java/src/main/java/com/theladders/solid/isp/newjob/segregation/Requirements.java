package com.theladders.solid.isp.newjob.segregation;

import java.util.Collection;
import java.util.List;

import com.theladders.solid.isp.newjob.JobRequirements;
import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;

public class Requirements implements JobRequirements
{
  private Experience              experience;
  private List<Discipline>        disciplines;
  private Collection<JobFunction> jobFunctions;
  private boolean                 jobReq;

  public Requirements(Experience experience,
                      List<Discipline> disciplines,
                      Collection<JobFunction> jobFunctions,
                      boolean jobReq)
  {
    this.experience = experience;
    this.disciplines = disciplines;
    this.jobFunctions = jobFunctions;
    this.jobReq = jobReq;
  }

  public List<Discipline> getDisciplines()
  {
    return disciplines;
  }

  public Experience getExperience()
  {
    return experience;
  }

  public Collection<JobFunction> getJobFunctions()
  {
    return jobFunctions;
  }

  public boolean isJobReq()
  {
    return jobReq;
  }
}
