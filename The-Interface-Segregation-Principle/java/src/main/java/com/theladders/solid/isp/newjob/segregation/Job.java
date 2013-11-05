package com.theladders.solid.isp.newjob.segregation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.newjob.*;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class Job implements com.theladders.solid.isp.oldjob.Job
{
  public Job(JobCompanyInfo companyInfo,
             JobCompensation compensation,
             JobEmployment employment,
             JobEntry entry,
             JobGeography geography,
             JobIndustryMembership industryMembership,
             JobIdentifiers identifiers,
             JobPosition position,
             JobPostStatus postStatus,
             JobPublication publication,
             JobRequirements requirements,
             JobVisibility visibility)
  {
    this.companyInfo = companyInfo;
    this.compensation = compensation;
    this.employment = employment;
    this.entry = entry;
    this.geography = geography;
    this.industryMembership = industryMembership;
    this.identifiers = identifiers;
    this.position = position;
    this.postStatus = postStatus;
    this.publication = publication;
    this.requirements = requirements;
    this.visibility = visibility;
  }

  // COMPANY INFO: SEARCH
  public String getCompany()
  {
    return companyInfo.getCompany();
  }

  public Integer getCompanySize()
  {
    return companyInfo.getCompanySize();
  }

  // COMPENSATION: SEARCH
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

  // EMPLOYMENT: JOB FILLING
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

  // ENTRY: POST
  public int getSubscriberId()
  {
    return entry.getSubscriberId();
  }

  public int getJobSiteId()
  {
    return entry.getJobSiteId();
  }

  public String getUrl()
  {
    return entry.getUrl();
  }

  public Date getEntryDate()
  {
    return entry.getEntryDate();
  }

  // GEOGRAPHY: SEARCH
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

  // INDUSTRY MEMBERSHIP: SEARCH
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

  // POSITION: SEARCH
  public String getTitle()
  {
    return position.getTitle();
  }

  public String getDescription()
  {
    return position.getDescription();
  }

  public String getShortDescription()
  {
    return position.getShortDescription();
  }

  public PositionLevel getPositionLevel()
  {
    return position.getPositionLevel();
  }

  // POST STATUS: SEARCH FILTERING
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

  // PUBLICATION
  public Date getPublicationDate()
  {
    return publication.getPublicationDate();
  }

  public Date getOriginalPublicationDate()
  {
    return publication.getOriginalPublicationDate();
  }

  public String getEditorNote()
  {
    return publication.getEditorNote();
  }

  // REQUIREMENTS: APPLY for JOB
  public List<Discipline> getDisciplines()
  {
    return requirements.getDisciplines();
  }

  public Experience getExperience()
  {
    return requirements.getExperience();
  }

  public Collection<JobFunction> getJobFunctions()
  {
    return requirements.getJobFunctions();
  }

  public boolean isJobReq()
  {
    return requirements.isJobReq();
  }

  // VISIBILITY: VIEW a JOB
  public boolean isAnonymous()
  {
    return visibility.isAnonymous();
  }

  public boolean isConfidential()
  {
    return visibility.isConfidential();
  }

  public boolean isExclusive()
  {
    return visibility.isExclusive();
  }

  public boolean isMarketing()
  {
    return visibility.isMarketing();
  }

  private JobCompanyInfo        companyInfo;
  private JobCompensation       compensation;
  private JobEmployment         employment;
  private JobEntry              entry;
  private JobGeography          geography;
  private JobIndustryMembership industryMembership;
  private JobIdentifiers        identifiers;
  private JobPosition           position;
  private JobPostStatus         postStatus;
  private JobPublication        publication;
  private JobRequirements       requirements;
  private JobVisibility         visibility;  
}
