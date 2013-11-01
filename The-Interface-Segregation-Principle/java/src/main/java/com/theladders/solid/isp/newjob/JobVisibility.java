package com.theladders.solid.isp.newjob;

public interface JobVisibility
{
  /**
   * @return returns true if this job was posted anonymously
   */
  boolean isAnonymous();

  boolean isConfidential();

  boolean isExclusive(); // This used to add a '*' to an exclusive job

  /**
   * Is this job a Marketing job? If this flag is set, basic access is allowed to this job (where
   * otherwise it would be premium) from certain landing pages.
   *
   * @return true if this is marked for marketing, false otherwise.
   */
  boolean isMarketing();
}
