package com.theladders.solid.isp.newjob.implementation;

import com.theladders.solid.isp.newjob.JobEmployment;

public class Employment implements JobEmployment
{
  private boolean filled;
  private boolean reimbursable;
  private String  reportsTo;

  public Employment(boolean filled,
                    boolean reimbursable,
                    String reportsTo)
  {
    this.filled = filled;
    this.reimbursable = reimbursable;
    this.reportsTo = reportsTo;
  }

  public boolean isFilled()
  {
    return filled;
  }

  public boolean isReimbursable()
  {
    return reimbursable;
  }

  public String getReportsTo()
  {
    return reportsTo;
  }
}
