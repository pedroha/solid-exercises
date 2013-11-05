package com.theladders.solid.isp.newjob.segregation;

import java.util.Date;

import com.theladders.solid.isp.newjob.*;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class Job implements FullJob // implements com.theladders.solid.isp.oldjob.Job
{
  private JobCompanyInfo        companyInfo;
  private JobCompensation       compensation;
  private JobEmployment         employment;
  private JobEntry              entry;
  private JobGeography          geography;
  private JobIndustryMembership industryMembership;
  private JobIdentifiers        identifiers;
  private JobPostStatus         postStatus;

  public Job()
  {
    this.companyInfo = new CompanyInfo("ibm", 1);
    this.compensation = new Compensation("$40000", "$20000", "$12000", "$500");
    this.employment = new Employment(false, false, "CTO");
    this.entry = new Entry(new Date(), "http://theladders.com/job/url");
    this.geography = new Geography("someLocation", new Region(), new City());
    this.industryMembership = new IndustryMembership(new Industry(), new Sector());
    this.identifiers = new Identifiers(10, new Date(), "Editor's note", 20, new Integer(10), false);
  }

  // COMPANY INFO: SEARCH, POST
  public String getCompany()
  {
    return companyInfo.getCompany();
  }

  public Integer getCompanySize()
  {
    return companyInfo.getCompanySize();
  }

  // COMPENSATION: SEARCH, POST
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
  
  // EMPLOYMENT
  public boolean isFilled()
  {
    return employment.isFilled();
  }

  public boolean isReimbursable()
  {
    return employment.isReimbursable();
  }

  public String getReportsTo()
  {
    return employment.getReportsTo();
  }
  
  // ENTRY
  public Date getEntryDate()
  {
    return entry.getEntryDate();
  }

  public String getUrl()
  {
    return entry.getUrl();
  }

  // GEOGRAPHY: SEARCH, POST
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

  // INDUSTRY MEMBERSHIP: SEARCH, POST
  public Industry getIndustry()
  {
    return industryMembership.getIndustry();
  }

  public Sector getSector()
  {
    return industryMembership.getSector();
  }

  // IDENTIFIERS: APPLY for JOB
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

  // POST STATUS
  public boolean isDeleted()
  {
    return postStatus.isDeleted();
  }

  public boolean isExpired()
  {
    return postStatus.isExpired();
  }

  public boolean hasStatus(JobStatus status)
  {
    return postStatus.hasStatus(status);
  }

  public Date getUpdateTime()
  {
    return postStatus.getUpdateTime();
  }
}
