package com.theladders.solid.isp.newjob;

import java.util.Date;

public interface JobPublication
{
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
  
  /**
   * Get the (internally set) editor's note.
   * 
   * @return editor's note.
   */
  String getEditorNote();
}
