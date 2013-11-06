package com.theladders.solid.srp;

import static org.junit.Assert.*;

import org.junit.*;

import com.theladders.solid.srp.business.JobApplicationUseCase;
import com.theladders.solid.srp.model.Job;
import com.theladders.solid.srp.model.Jobseeker;
import com.theladders.solid.srp.model.JobseekerProfile;
import com.theladders.solid.srp.model.ProfileStatus;
import com.theladders.solid.srp.model.Resume;
import com.theladders.solid.srp.model.job.application.SuccessfulApplication;
import com.theladders.solid.srp.persistence.ActiveResumeRepository;
import com.theladders.solid.srp.persistence.JobApplicationRepository;
import com.theladders.solid.srp.persistence.JobRepository;
import com.theladders.solid.srp.persistence.JobseekerProfileRepository;
import com.theladders.solid.srp.persistence.ResumeRepository;
import com.theladders.solid.srp.services.JobApplicationManager;
import com.theladders.solid.srp.services.JobseekerProfileManager;
import com.theladders.solid.srp.services.MyResumeManager;
import com.theladders.solid.srp.services.ResumeManager;
import com.theladders.solid.srp.util.Result;
import com.theladders.solid.srp.util.ResumeFile;
import com.theladders.solid.srp.util.ResumeProfile;


public class Test_JobApplicationUseCase
{
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

  @Before
  public void setup()
  {
    setupJobseekerProfileRepository();
    setupJobRepository();
    setupResumeRepository();
    setupActiveResumeRepository();
    setupJobApplicationRepository();    
  }

  private static final int INVALID_JOB_ID        = 555;
  private static final String SHARED_RESUME_NAME = "A Resume";
  private static final int JOBSEEKER_WITH_RESUME = 777;
  private static final int INCOMPLETE_JOBSEEKER  = 888;
  private static final int APPROVED_JOBSEEKER    = 1010;
  
  private JobRepository              jobRepository;
  private ResumeRepository           resumeRepository;
  private JobApplicationRepository   jobApplicationRepository;
  private JobseekerProfileRepository jobseekerProfileRepository;
  private ActiveResumeRepository     activeResumeRepository;

  
  private void setupJobseekerProfileRepository()
  {
    jobseekerProfileRepository = new JobseekerProfileRepository();

    addToJobseekerProfileRepository(APPROVED_JOBSEEKER, ProfileStatus.APPROVED);
    addToJobseekerProfileRepository(INCOMPLETE_JOBSEEKER, ProfileStatus.INCOMPLETE);
    addToJobseekerProfileRepository(JOBSEEKER_WITH_RESUME, ProfileStatus.APPROVED);
  }

  private void addToJobseekerProfileRepository(int id, ProfileStatus status)
  {
    JobseekerProfile profile = new JobseekerProfile(id, status);
    jobseekerProfileRepository.addProfile(profile);
  }

  private void setupJobRepository()
  {
    jobRepository = new JobRepository();

    addJobToRepository(5);
    addJobToRepository(15);
    addJobToRepository(51);
    addJobToRepository(57);
    addJobToRepository(501);
    addJobToRepository(1555);
    addJobToRepository(5012);
    addJobToRepository(50111);
  }

  private void addJobToRepository(int jobId)
  {
    if (jobId != INVALID_JOB_ID)
    {
      jobRepository.addJob(new Job(jobId));
    }
  }

  private void setupResumeRepository()
  {
    resumeRepository = new ResumeRepository();
  }

  private void setupActiveResumeRepository()
  {
    activeResumeRepository = new ActiveResumeRepository();

    activeResumeRepository.makeActive(JOBSEEKER_WITH_RESUME, new Resume("Blammo"));
  }

  private void setupJobApplicationRepository()
  {
    jobApplicationRepository = new JobApplicationRepository();

    addToJobApplicationRepository();
  }

  private void addToJobApplicationRepository()
  {
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(15);
    Resume resume = new Resume("foo");

    SuccessfulApplication existingApplication = new SuccessfulApplication(JOBSEEKER, job, resume);

    jobApplicationRepository.add(existingApplication);
  }

  private JobApplicationUseCase createJobApplicationUseCase()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    JobApplicationManager jobApplicationManager = new JobApplicationManager(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(resumeRepository);
    MyResumeManager myResumeManager = new MyResumeManager(activeResumeRepository);

    JobApplicationUseCase jobApplication = new JobApplicationUseCase(jobseekerProfileManager,
                                                                     jobApplicationManager,
                                                                     resumeManager,
                                                                     myResumeManager);

    return jobApplication;
  }
}
