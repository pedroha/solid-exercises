package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.persistence.ActiveResumeRepository;

public class MyResumeManager
{
  private final ActiveResumeRepository repository;

  public MyResumeManager(ActiveResumeRepository repository)
  {
    this.repository = repository;
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    repository.makeActive(jobseeker.getId(), resume);
  }

  public Resume getActiveResume(int jobseekerId)
  {
    return repository.activeResumeFor(jobseekerId);
  }
}
