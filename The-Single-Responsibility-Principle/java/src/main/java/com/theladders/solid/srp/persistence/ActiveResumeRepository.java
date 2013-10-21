package com.theladders.solid.srp.persistence;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.model.Resume;


public class ActiveResumeRepository
{
  private final Map<Integer, Resume> resumes;

  public ActiveResumeRepository()
  {
    this.resumes = new HashMap<>();
  }

  public Resume activeResumeFor(int jobseekerId)
  {
    return resumes.get(jobseekerId);
  }

  public void makeActive(int jobseekerId, Resume resume)
  {
    resumes.put(jobseekerId, resume);
  }
}
