package com.theladders.solid.srp.services;

import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.persistence.ResumeRepository;

public class ResumeManager
{
  private final ResumeRepository resumeRepository;

  public ResumeManager(ResumeRepository resumeRepository)
  {
    this.resumeRepository = resumeRepository;
  }

  public Resume saveResume(Jobseeker jobseeker,
                           String fileName)
  {

    Resume resume = new Resume(fileName);
    resumeRepository.saveResume(jobseeker.getId(), resume);

    return resume;
  }
}
