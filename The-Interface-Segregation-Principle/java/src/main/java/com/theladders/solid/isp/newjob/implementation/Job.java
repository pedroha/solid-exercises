package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.*;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.Region;

public class Job implements FullJob
{
  private JobApplicationStatus  applicationStatus;
  private JobCompensation       compensation;
  private CompanyInfo           companyInfo;
  private JobGeography          geography;
  
  public Job()
  {
    this.applicationStatus = new ApplicationStatus(false, false, false);
    this.compensation = new Compensation("$40000", "$20000", "$12000", "$500");
    this.companyInfo = new CompanyInfo("ibm", 1);
    this.geography = new Geography("someLocation", new Region(), new City());
  }
  
  // COMPENSATION
  public String getCompensation()
  {
    return compensation.getCompensation();
  }

  public String getCompensationSalary()
  {
    return compensation.getCompensationSalary();
  }

  public String getCompensationBonus()
  {
    return compensation.getCompensationBonus();
  }

  public String getCompensationOther()
  {
    return compensation.getCompensationOther();
  }

  // APPLICATION STATUS
  public boolean isDeleted()
  {
    return applicationStatus.isDeleted();
  }

  public boolean isExpired()
  {
    return applicationStatus.isExpired();
  }

  public boolean isFilled()
  {
    return applicationStatus.isFilled();
  }

  public boolean hasStatus(JobStatus status)
  {
    return applicationStatus.hasStatus(status);
  }

  // COMPANY INFO
  public String getCompany()
  {
    return companyInfo.getCompany();
  }

  public Integer getCompanySize()
  {
    return companyInfo.getCompanySize();
  }

  // GEOGRAPHY
  public String getLocation()
  {
    return geography.getLocation();
  }

  public Region getRegion()
  {
    return geography.getRegion();
  }

  public City getCity()
  {
    return geography.getCity();
  }
}
