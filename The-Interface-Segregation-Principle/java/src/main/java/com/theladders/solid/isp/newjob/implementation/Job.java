package com.theladders.solid.isp.newjob.implementation;

import java.util.Date;

import com.theladders.solid.isp.newjob.*;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class Job implements FullJob // implements com.theladders.solid.isp.oldjob.Job
{
  private JobPostStatus         applicationStatus;
  private JobCompensation       compensation;
  private JobCompanyInfo        companyInfo;
  private JobGeography          geography;
  private JobIndustryMembership industryMembership;
  private JobIdentifiers        identifiers;

  public Job()
  {
    this.applicationStatus = new ApplicationStatus(false, false, false);
    this.compensation = new Compensation("$40000", "$20000", "$12000", "$500");
    this.companyInfo = new CompanyInfo("ibm", 1);
    this.geography = new Geography("someLocation", new Region(), new City());
    this.industryMembership = new IndustryMembership(new Industry(), new Sector());
    this.identifiers = new Identifiers(10, new Date(), "Editor's note", 20, new Integer(10), false);
  }

  // JOB POST STATUS
  public boolean isDeleted()
  {
    return applicationStatus.isDeleted();
  }

  public boolean isExpired()
  {
    return applicationStatus.isExpired();
  }

  public boolean hasStatus(JobStatus status)
  {
    return applicationStatus.hasStatus(status);
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

  // INDUSTRY MEMBERSHIP
  public Industry getIndustry()
  {
    return industryMembership.getIndustry();
  }

  public Sector getSector()
  {
    return industryMembership.getSector();
  }

  // IDENTIFIERS
  public int getOldJobId()
  {
    return identifiers.getOldJobId();
  }

  public int getJobId()
  {
    return identifiers.getJobId();
  }

  public Integer getParentJobId()
  {
    return identifiers.getParentJobId();
  }
  
}
