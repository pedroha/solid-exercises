package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.JobCompanyInfo;

public class CompanyInfo implements JobCompanyInfo
{
  public CompanyInfo(String company, Integer companySize)
  {
    this.company = company;
    this.companySize = companySize;
  }

  public String getCompany()
  {
    return company;
  }

  public Integer getCompanySize()
  {
    return companySize;
  }

  private String  company;
  private Integer companySize;
}
