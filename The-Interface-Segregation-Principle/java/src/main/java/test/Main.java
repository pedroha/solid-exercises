package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.newjob.segregation.CompanyInfo;
import com.theladders.solid.isp.newjob.segregation.Compensation;
import com.theladders.solid.isp.newjob.segregation.Employment;
import com.theladders.solid.isp.newjob.segregation.Entry;
import com.theladders.solid.isp.newjob.segregation.Geography;
import com.theladders.solid.isp.newjob.segregation.Identifiers;
import com.theladders.solid.isp.newjob.segregation.IndustryMembership;
import com.theladders.solid.isp.newjob.segregation.Job;
import com.theladders.solid.isp.newjob.segregation.Position;
import com.theladders.solid.isp.newjob.segregation.PostStatus;
import com.theladders.solid.isp.newjob.segregation.Publication;
import com.theladders.solid.isp.newjob.segregation.Requirements;
import com.theladders.solid.isp.newjob.segregation.Visibility;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class Main
{
  void testJobInstation()
  {
    int companySizeType = 1;
    boolean isfilled = false;
    boolean isReimbursable = false;
    Date entryDate = new Date();
    int subscriberId = 0;
    int jobSiteId = 10;

    CompanyInfo companyInfo = new CompanyInfo("ibm", companySizeType);
    Compensation compensation = new Compensation("$40000", "$20000", "$12000", "$500");
    Employment employment = new Employment(isfilled, isReimbursable, "CTO");
    Entry entry = new Entry(subscriberId, jobSiteId, "http://theladders.com/job/url", entryDate);

    Geography geography = new Geography("someLocation", new Region(), new City());
    IndustryMembership industryMembership = new IndustryMembership(new Industry(), new Sector());

    int jobId = 10;
    int parentJobId = 11;
    int oldJobId = 13;
    Identifiers identifiers = new Identifiers(jobId, parentJobId, oldJobId);

    String title = "Backend Developer";
    String description = "Develop Java REST Java web services";
    String shortDescription = "Java REST";
    Position position = new Position(title, description, shortDescription, new PositionLevel());

    boolean deleted = false;
    boolean expired = false;
    Date updateTime = new Date();
    PostStatus postStatus = new PostStatus(deleted, expired, updateTime);

    String editorNote = "Some note";
    Date publicationDate = new Date();
    Date originalPublicationDate = new Date();
    Publication publication = new Publication(editorNote, publicationDate, originalPublicationDate);

    Experience experience = new Experience();
    List<Discipline> disciplines = new ArrayList<>();
    Collection<JobFunction> jobFunctions = new ArrayList<>();
    boolean jobReq = false;
    
    Requirements requirements = new Requirements(experience, disciplines, jobFunctions, jobReq);
    
    boolean anonymous = false;
    boolean confidential = true;
    boolean exclusive = false;
    boolean isMarketing = true;
    
    Visibility visibility = new Visibility(anonymous, confidential, exclusive, isMarketing);

    Job job = new Job(companyInfo,
                      compensation,
                      employment,
                      entry,
                      geography,
                      industryMembership,
                      identifiers,
                      position,
                      postStatus,
                      publication,
                      requirements,
                      visibility);
    
    System.out.println(job.getCompany());
  }
}
