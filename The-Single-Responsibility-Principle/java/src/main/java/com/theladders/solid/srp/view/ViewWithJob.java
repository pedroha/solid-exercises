package com.theladders.solid.srp.view;

import com.theladders.solid.srp.model.Job;

public abstract class ViewWithJob extends View
{
  private void setJob(Job job)
  {
    putData("jobId", job.getJobId());
    putData("jobTitle", job.getTitle());
  }
  
  @Override
  public void putData(String key, Object value)
  {
    if (key.equals("job"))
    {
      setJob((Job)value);
    }
    else
    {
      super.putData(key, value);
    }
  }
}
