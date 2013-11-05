package com.theladders.solid.isp.newjob.segregation;

import java.util.Date;

import com.theladders.solid.isp.newjob.JobPublication;

public class Publication implements JobPublication
{
  private String editorNote;
  private Date   publicationDate;
  private Date   originalPublicationDate;

  public Publication(String editorNote,
                     Date publicationDate,
                     Date originalPublicationDate)
  {
    this.editorNote = editorNote;
    this.publicationDate = publicationDate;
    this.originalPublicationDate = originalPublicationDate;
  }

  public String getEditorNote()
  {
    return editorNote;
  }

  public Date getPublicationDate()
  {
    return publicationDate;
  }

  public Date getOriginalPublicationDate()
  {
    return originalPublicationDate;
  }
}
