package com.theladders.solid.srp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.util.JobApplicationPresenter;
import com.theladders.solid.srp.util.Result;

public class JobApplicationPresenterImpl implements JobApplicationPresenter
{
  public Result error(String errorMessage)
  {
    List<String> errList = new ArrayList<>();
    errList.add("We could not process your application.");

    Map<String, Object> model = new HashMap<>();
    return new Result("error", model, errList);
  }

  public Result success(Job job)
  {
    return new Result("success", getJobModel(job));
  }

  public Result completeProfile(Job job)
  {
    return new Result("completeResumePlease", getJobModel(job));
  }

  public Result invalidJob(int jobId)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId",  jobId);
    return new Result("invalidJob", model);
  }
  
  private static Map<String, Object> getJobModel(Job job)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());
    return model;
  }
}
