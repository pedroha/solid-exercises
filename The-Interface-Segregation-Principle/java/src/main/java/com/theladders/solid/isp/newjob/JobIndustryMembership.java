package com.theladders.solid.isp.newjob;

import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public interface JobIndustryMembership
{
  Industry getIndustry();

  Sector getSector();
}
