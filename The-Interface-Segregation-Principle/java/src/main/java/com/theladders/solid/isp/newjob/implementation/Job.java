package com.theladders.solid.isp.newjob.implementation;

import java.util.Date;

import com.theladders.solid.isp.newjob.*;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class Job implements FullJob //implements com.theladders.solid.isp.oldjob.Job
{
  private JobApplicationStatus          applicationStatus;
  private JobCompensation               compensation;
  private CompanyInfo                   companyInfo;
  private JobGeography                  geography;
  private JobIndustryMembership         industryMembership;  
  private JobInternal                   internal;

  public Job()
  {
    this.applicationStatus = new ApplicationStatus(false, false, false);
    this.compensation = new Compensation("$40000", "$20000", "$12000", "$500");
    this.companyInfo = new CompanyInfo("ibm", 1);
    this.geography = new Geography("someLocation", new Region(), new City());
    this.industryMembership = new IndustryMembership(new Industry(), new Sector());
    this.internal = new Internal(10, new Date(), "Editor's note", 20, new Integer(10), false);
  
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

  // INDUSTRY MEMBERSHIP
  public Industry getIndustry()
  {
    return industryMembership.getIndustry();
  }

  public Sector getSector()
  {
    return industryMembership.getSector();
  }
  
  // INTERNAL
  public int getOldJobId()
  {
    return internal.getOldJobId();
  }

  public Date getUpdateTime()
  {
    return internal.getUpdateTime();
  }

  public String getEditorNote()
  {
    return internal.getEditorNote();
  }

  public int getJobId()
  {
    return internal.getJobId();
  }

  public Integer getParentJobId()
  {
    return internal.getParentJobId();
  }

  public boolean isJobReq()
  {
    return internal.isJobReq();
  }

  
}
