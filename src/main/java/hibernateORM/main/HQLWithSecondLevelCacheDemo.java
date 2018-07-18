package hibernateORM.main;

import hibernateORM.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HQLWithSecondLevelCacheDemo {
  private static Logger logger = LoggerFactory.getLogger(HQLWithSecondLevelCacheDemo.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();


    //Different Session. 2nd level caching is used
    Session session3 = sf.openSession();
    Query query1 =  session3.createQuery("from User where username='Tariq'");
    query1.setCacheable(true);
    User user3 = (User) query1.uniqueResult();
    logger.info("{}",user3);
    session3.close();

    Session session4 = sf.openSession();
    Query query2 =  session4.createQuery("from User where username='Tariq'");
    query2.setCacheable(true);
    User user4 = (User) query2.uniqueResult();
    logger.info("{}",user4);
    session4.close();

  }


}
