package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.JobCompensation;

public class Compensation implements JobCompensation
{
  public Compensation(String compensation,
                      String compensationSalary,
                      String compensationBonus,
                      String compensationOther)
  {
    this.compensation = compensation;
    this.compensationSalary = compensationSalary;
    this.compensationBonus = compensationBonus;
    this.compensationOther = compensationOther;
  }

  public String getCompensation()
  {
    return compensation;
  }

  public String getCompensationSalary()
  {
    return compensationSalary;
  }

  public String getCompensationBonus()
  {
    return compensationBonus;
  }

  public String getCompensationOther()
  {
    return compensationOther;
  }
  
  private String compensation;
  private String compensationSalary;
  private String compensationBonus;
  private String compensationOther;
}
