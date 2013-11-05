package com.theladders.solid.isp.newjob.segregation;

import com.theladders.solid.isp.newjob.JobPosition;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;

public class Position implements JobPosition
{
  private String        title;
  private String        description;
  private String        shortDescription;
  private PositionLevel positionLevel;

  public Position(String title,
                  String description,
                  String shortDescription,
                  PositionLevel positionLevel)
  {
    this.title = title;
    this.description = description;
    this.shortDescription = shortDescription;
    this.positionLevel = positionLevel;
  }

  public String getTitle()
  {
    return title;
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
}
