package com.theladders.solid.srp.http;

import com.theladders.solid.srp.model.Jobseeker;

public class HttpSession
{
  private final Jobseeker jobseeker;

  public HttpSession(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }

  public Jobseeker getJobseeker()
  {
    return jobseeker;
  }
}
