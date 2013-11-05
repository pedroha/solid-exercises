package com.theladders.solid.isp.newjob;

public interface JobCompensation extends JobSearch
{
  String getCompensation();

  String getCompensationSalary();

  String getCompensationBonus();

  String getCompensationOther();
}
