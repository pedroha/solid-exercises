package com.theladders.solid.isp.newjob;

import java.util.Date;

public interface JobPublication
{
  Date getEntryDate();

  /**
   * Get the date this job was published.
   *
   * @return the Date the job was published.
   */
  Date getPublicationDate();

  /**
   * Get the date this job was originally published
   *
   * @return the Date the job was originally published
   */
  Date getOriginalPublicationDate();
  
  Date getUpdateTime();

  /**
   * Get the (internally set) editor's note.
   * 
   * @return editor's note.
   */
  String getEditorNote();

  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   *
   * @return URL for this job.
   */
  String getUrl();
}
