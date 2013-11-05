package com.theladders.solid.isp.newjob.segregation;

import com.theladders.solid.isp.newjob.JobVisibility;

public class Visibility implements JobVisibility
{
  private boolean anonymous;
  private boolean confidential;
  private boolean exclusive;
  private boolean isMarketing;
  
  public Visibility(boolean anonymous,
                    boolean confidential,
                    boolean exclusive,
                    boolean isMarketing)
  {
    this.anonymous = anonymous;
    this.confidential = confidential;
    this.exclusive = exclusive;
    this.isMarketing = isMarketing;
  }

  public boolean isAnonymous()
  {
    return anonymous;
  }

  public boolean isConfidential()
  {
    return confidential;
  }

  public boolean isExclusive()
  {
    return exclusive;
  }

  public boolean isMarketing()
  {
    return isMarketing;
  }
}
