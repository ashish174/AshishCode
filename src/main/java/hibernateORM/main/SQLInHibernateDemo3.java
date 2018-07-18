package hibernateORM.main;

import hibernateORM.entity.Kid;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SQLInHibernateDemo3 {
  private static Logger logger = LoggerFactory.getLogger(SQLInHibernateDemo3.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Kid.class);
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    //SQLCustomQuery sqlQuery = session.createSql
    NativeQuery nativeQuery = session.createNativeQuery("select * from Kids");
    nativeQuery.addEntity(Kid.class);
    List<Kid> kids = nativeQuery.list();
    for(Kid kid : kids){
      logger.info("{} ", kid);
    }

    NativeQuery nativeQuery1 = session.createNativeQuery("select name, marks from Kids where marks>80");
    List<Object[]> kidsWithMark = nativeQuery1.list();
    for(Object[] obj : kidsWithMark){
      logger.info("{} : {}", obj[0], obj[1]);
    }

    /*
    Query query2 = session.createQuery("select sum(marks) from Kid where marks>50");
    long totalmark = (long) query2.uniqueResult();
    logger.info("TotalMark : {}", totalmark);

    int b = 80;
    Query query3 = session.createQuery("select sum(marks) from Kid where marks> :b");
    query3.setParameter("b", 50);
    long totalmark1 = (long) query3.uniqueResult();
    logger.info("TotalMark : {}", totalmark1);*/

    tx.commit();
  }


}
