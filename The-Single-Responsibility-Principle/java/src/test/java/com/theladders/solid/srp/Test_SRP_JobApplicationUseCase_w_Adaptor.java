package com.theladders.solid.srp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.business.helpers.ManagerActions;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;

public class Test_SRP_JobApplicationUseCase_w_Adaptor
{
  private static final int INVALID_JOB_ID        = 555;
  private static final String SHARED_RESUME_NAME = "A Resume";
  private static final int JOBSEEKER_WITH_RESUME = 777;
  private static final int INCOMPLETE_JOBSEEKER  = 888;
  private static final int APPROVED_JOBSEEKER    = 1010;
  
  @Test
  public void invalidJobId()
  {
    JobApplicationUseCase application = createJobApplicationUseCase();
    
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(INVALID_JOB_ID);
    int jobId = INVALID_JOB_ID;
    
    ResumeProfile resumeProfile = new ResumeProfile(new ResumeFile("My Resume"), false, false);
    
    Result result = application.applyForJob(JOBSEEKER, job, jobId, resumeProfile);
    
    assertEquals(result.getType(), "invalidJob");
    assertEquals(result.get("jobId"), jobId);
  }

  public void success()
  {

  }

  public void completeProfile()
  {

  }

  public void error()
  {

  }

  private static JobApplicationUseCase createJobApplicationUseCase()
  {

    ManagerActions managerActions = new TestManagerAdapter();
    JobApplicationUseCase jobApplication = new JobApplicationUseCase(managerActions);
    return jobApplication;
  }
}
