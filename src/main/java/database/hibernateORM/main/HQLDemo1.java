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

public class HQLDemo1 {
  private static Logger logger = LoggerFactory.getLogger(HQLDemo1.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Kid.class);
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    Query query = session.createQuery("from Kid");
    List<Kid> kids = query.list();
    for(Kid kid : kids){
      logger.info("{}", kid);
    }

    Query query1 = session.createQuery("from Kid where rollNo=2");
    Kid kid = (Kid) query1.uniqueResult();
    logger.info("{}", kid);

    Query query2 = session.createQuery("from Kid where marks>50");
    List<Kid> kids1 = query2.list();
    for(Kid kid1 : kids1){
      logger.info("{}", kid1);
    }

    tx.commit();
  }


}
