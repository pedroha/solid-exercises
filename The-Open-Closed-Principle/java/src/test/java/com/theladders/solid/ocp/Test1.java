package com.theladders.solid.ocp;

import org.junit.*;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.resume.ConfidentialResumeHandler;
import com.theladders.solid.ocp.resume.JobseekerProfileManager;
import com.theladders.solid.ocp.resume.ResumeConfidentialityManager;
import com.theladders.solid.ocp.user.User;

import junit.framework.TestCase;

public class Test1 extends TestCase
{
  private User user;
  private ResumeConfidentialityManager resumeConfidentialityManager;
  @Before
  public void setup()
  {   
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager();
    JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao = new JobseekerConfidentialityProfileDao();
    ConfidentialResumeHandler confidentialResumeHandler = new ConfidentialResumeHandler(jobseekerProfileManager, jobseekerConfidentialityProfileDao);
    this.resumeConfidentialityManager = new ResumeConfidentialityManager(confidentialResumeHandler);

    int id = 1; 
    this.user = new User(id);
  }
  
  @Test
  public void testMakeAllContactInfoNonConfidential()
  {
    
    // check beforehand all data is confidential
    User user = new User(4);
    
    // execute
    
    resumeConfidentialityManager.makeAllContactInfoNonConfidential(user);
    
    // check changes have taken before
    
    
    assertTrue(false);
    assertEquals(false, true);
  }

  @Test
  public void testMakeAllCategoriesNonConfidential()
  {
    // check beforehand all data is confidential
    User user = new User(2);
    
    
    
    // execute
    resumeConfidentialityManager.makeAllCategoriesNonConfidential(user);
    
    // check changes have taken before
  
  }
  
  private void makeAllConfidential(User user)
  {
    
  }
}
