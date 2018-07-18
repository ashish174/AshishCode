package hibernateORM.main;

import hibernateORM.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondLevelCacheDemo {
  private static Logger logger = LoggerFactory.getLogger(SecondLevelCacheDemo.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();


    //Different Session. 2nd level caching is used
    Session session3 = sf.openSession();
    Transaction tx3 = session3.beginTransaction();
    User user3 = session3.get(User.class, "Anshu");
    logger.info("{}",user3);
    tx3.commit();
    session3.close();

    Session session4 = sf.openSession();
    Transaction tx4 = session4.beginTransaction();
    User user4 = session4.get(User.class, "Anshu");
    logger.info("{}",user4);
    tx4.commit();
    session4.close();

  }


}
