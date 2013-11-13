package main.refactored;

import java.util.List;

import com.theladders.solid.dip.refactored.*;

public class Main
{
  public static void main(String[] args)
  {
    SuggestedArticleDao dao = new SuggestedArticleDao();
    RepositoryManager   repository = new RepositoryManager();
    ResourceMapper      resourceMapper = new CategoryResourceMapper();
    ContentRepository   contentRepository = new ArticleContentRepository(repository, resourceMapper);

    SubscriberArticleManagerImpl s = new SubscriberArticleManagerImpl(dao, contentRepository);

    Subscriber subscriber = new Subscriber()
    {
      public int getSubscriberId()
      {
        return 9;
      }
    };

    List<? extends SuggestedArticle> articles = s.getArticlesbySubscriber(subscriber);

    for (SuggestedArticle article : articles)
    {
      ArticleId id = article.getSuggestedArticleId();
      ContentNode node = article.getContent();
      Object primaryTopic = node.getProperty("primaryTopic");
      Object miniImagePath = node.getProperty("miniImagePath");
      String note = article.getNote();

      System.out.println("----------------------------");
      System.out.println("ID: " + id.value());
      System.out.println("Primary Topic: " + primaryTopic);
      System.out.println("MiniImagePath: " + miniImagePath);
      System.out.println("Note: " + note);
    }
  }
}
