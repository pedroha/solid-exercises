package com.theladders.solid.isp.newjob;

import com.theladders.solid.isp.oldjob.stubs.PositionLevel;

public interface JobPosition
{
  String getTitle();
  String getReportsTo();

  /**
   * Refactored so it can be used by both job and JobReq
   * @return fullJobDescription()
   *
   */
  String getDescription();
  
  String getShortDescription();
  PositionLevel getPositionLevel();
}
