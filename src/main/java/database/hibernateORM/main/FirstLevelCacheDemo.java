package database.hibernateORM.main;

import database.hibernateORM.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstLevelCacheDemo {
  private static Logger logger = LoggerFactory.getLogger(FirstLevelCacheDemo.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();

    Session session = sf.openSession();
    Transaction tx = session.beginTransaction();
    //Cached
    User user1 = session.get(User.class, "Anshu");
    logger.info("{}",user1);
    User user2 = session.get(User.class, "Anshu");
    logger.info("{}",user2);
    tx.commit();


    //Different Session. Hence Caching is not shared
    Session session3 = sf.openSession();
    Transaction tx3 = session3.beginTransaction();
    User user3 = session3.get(User.class, "Anshu");
    logger.info("{}",user3);
    tx3.commit();

    Session session4 = sf.openSession();
    Transaction tx4 = session4.beginTransaction();
    User user4 = session4.get(User.class, "Anshu");
    logger.info("{}",user4);
    tx4.commit();

  }


}
