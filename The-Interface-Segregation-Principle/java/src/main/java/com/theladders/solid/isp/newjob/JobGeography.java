package com.theladders.solid.isp.newjob;

import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public interface JobGeography
{
  String getLocation();

  Region getRegion();

  City getCity();
}
