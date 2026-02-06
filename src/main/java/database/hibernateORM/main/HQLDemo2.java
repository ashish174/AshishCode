package database.hibernateORM.main;

import database.hibernateORM.entity.Kid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HQLDemo2 {
  private static Logger logger = LoggerFactory.getLogger(HQLDemo2.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Kid.class);
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    Query query = session.createQuery("select rollNo, name, marks from Kid");
    List<Object[]> kids = query.list();
    for(Object[] kid : kids){
      logger.info("{} : {} : {}", kid[0], kid[1], kid[2]);
    }

    Query query1 = session.createQuery("select marks from Kid where rollNo=2");
    int mark = (int) query1.uniqueResult();
    logger.info("Marks : {}", mark);

    Query query2 = session.createQuery("select sum(marks) from Kid where marks>50");
    long totalmark = (long) query2.uniqueResult();
    logger.info("TotalMark : {}", totalmark);

    int b = 80;
    Query query3 = session.createQuery("select sum(marks) from Kid where marks> :b");
    query3.setParameter("b", 50);
    long totalmark1 = (long) query3.uniqueResult();
    logger.info("TotalMark : {}", totalmark1);

    tx.commit();
  }


}
