package com.theladders.solid.isp.newjob.segregation;

import com.theladders.solid.isp.newjob.JobPosition;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;

public class Position implements JobPosition
{
  private String        title;
  private String        reportsTo;
  private String        description;
  private String        shortDescription;
  private PositionLevel positionLevel;
  private boolean       reimbursable;

  public Position(String title,
                  String reportsTo,
                  String description,
                  String shortDescription,
                  PositionLevel positionLevel,
                  boolean reimbursable)
  {
    this.title = title;
    this.reportsTo = reportsTo;
    this.description = description;
    this.shortDescription = shortDescription;
    this.positionLevel = positionLevel;
    this.reimbursable = reimbursable;
  }

  public String getTitle()
  {
    return title;
  }

  public String getReportsTo()
  {
    return reportsTo;
  }

  public String getDescription()
  {
    return description;
  }

  public String getShortDescription()
  {
    return shortDescription;
  }

  public PositionLevel getPositionLevel()
  {
    return positionLevel;
  }

  public boolean isReimbursable()
  {
    return reimbursable;
  }
}
