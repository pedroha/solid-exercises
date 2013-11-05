package com.theladders.solid.isp.newjob.segregation;

import com.theladders.solid.isp.newjob.JobIndustryMembership;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class IndustryMembership implements JobIndustryMembership
{
  public IndustryMembership(Industry industry,
                            Sector sector)
  {
    this.industry = industry;
    this.sector = sector;
  }

  public Industry getIndustry()
  {
    return industry;
  }

  public Sector getSector()
  {
    return sector;
  }
  
  private Industry      industry;
  private Sector        sector;  
}
