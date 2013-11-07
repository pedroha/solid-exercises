package test;

import java.util.List;

import com.theladders.solid.dip.original.ContentNode;
import com.theladders.solid.dip.original.RepositoryManager;
import com.theladders.solid.dip.original.SubscriberArticleManagerImpl;
import com.theladders.solid.dip.original.SuggestedArticle;
import com.theladders.solid.dip.original.SuggestedArticleDao;

public class Main
{
  public static void main(String[] args)
  {
    SuggestedArticleDao dao = new SuggestedArticleDao();
    RepositoryManager repository = new RepositoryManager();
    
    SubscriberArticleManagerImpl s = new SubscriberArticleManagerImpl(dao, repository);
    
    int subscriberId = 9;
    List<SuggestedArticle> articles = s.getArticlesbySubscriber(subscriberId);
    
    for (SuggestedArticle article: articles)
    {
      Integer id = article.getSuggestedArticleId();
      ContentNode node = article.getContent();
      Object primaryTopic = node.getProperty("primaryTopic");
      Object miniImagePath = node.getProperty("miniImagePath");
      String note = article.getNote();

      System.out.println("----------------------------");
      System.out.println("ID: " + id);
      System.out.println("Primary Topic: " + primaryTopic);
      System.out.println("MiniImagePath: " + miniImagePath);
      System.out.println("Note: " + note);
    }
  }
}
