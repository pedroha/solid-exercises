package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.JobGeography;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public class Geography implements JobGeography
{
  private String location;
  private Region region;
  private City   city;
  
  public Geography(String location,
                   Region region,
                   City city)
  {
    super();
    this.location = location;
    this.region = region;
    this.city = city;
  }

  public String getLocation()
  {
    return location;
  }

  public Region getRegion()
  {
    return region;
  }

  public City getCity()
  {
    return city;
  }
}
