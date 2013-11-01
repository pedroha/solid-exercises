package com.theladders.solid.isp.newjob;

import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public interface JobGeography extends JobSearch
{
  String getLocation();

  Region getRegion();

  City getCity();
}
