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
    int jobId = INVALID_JOB_ID;
    Job job = null; // Invalid job
    
    ResumeProfile resumeProfile = new ResumeProfile(new ResumeFile("My Resume"), false, false);
    
    Result result = application.applyForJob(JOBSEEKER, job, jobId, resumeProfile);

    assertEquals(result.getType(), "invalidJob");
    assertEquals(result.get("jobId"), jobId);
  }

  @Test
  public void success()
  {
    JobApplicationUseCase application = createJobApplicationUseCase();
    
    Jobseeker JOBSEEKER = new Jobseeker(JOBSEEKER_WITH_RESUME, true);
    int jobId = 25;
    Job job = new Job(jobId, "Full-stack developer");
    
    ResumeProfile resumeProfile = new ResumeProfile(new ResumeFile("My Resume"), false, false);
    
    Result result = application.applyForJob(JOBSEEKER, job, jobId, resumeProfile);

    assertEquals(result.getType(), "success");
    assertEquals(result.get("jobId"), new Integer(jobId));
    assertEquals(result.get("jobTitle"), job.getTitle());
  }

  @Test
  public void completeProfile()
  {
    JobApplicationUseCase application = createJobApplicationUseCase();
    
    Jobseeker JOBSEEKER = new Jobseeker(JOBSEEKER_WITH_RESUME, true);
    int jobId = 25;
    Job job = new Job(jobId, "Full-stack developer");
    
    ResumeProfile resumeProfile = new ResumeProfile(new ResumeFile("My Resume"), false, false);
    
    Result result = application.applyForJob(JOBSEEKER, job, jobId, resumeProfile);

    assertEquals(result.getType(), "needsProfileCompletion");
    assertEquals(result.get("jobId"), new Integer(jobId));
    assertEquals(result.get("jobTitle"), job.getTitle());
  }

  @Test
  public void error()
  {
    JobApplicationUseCase application = createJobApplicationUseCase();
    
    Jobseeker JOBSEEKER = new Jobseeker(JOBSEEKER_WITH_RESUME, true);
    int jobId = 25;
    Job job = new Job(jobId, "Full-stack developer");
    
    String resumeFileName = null;
    ResumeProfile resumeProfile = new ResumeProfile(new ResumeFile(resumeFileName), false, false);
    
    Result result = application.applyForJob(JOBSEEKER, job, jobId, resumeProfile);

    assertEquals(result.getType(), "error");    
    assertEquals(result.getError(), "We could not process your application.");
  }

  private static JobApplicationUseCase createJobApplicationUseCase()
  {
    ManagerActions managerActions = new TestManagerAdapter();
    JobApplicationUseCase jobApplication = new JobApplicationUseCase(managerActions);
    return jobApplication;
  }
}
