package hibernateORM.main;

import hibernateORM.entity.Kid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateStateDemo {
  private static Logger logger = LoggerFactory.getLogger(HibernateStateDemo.class);


  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure().addAnnotatedClass(Kid.class);
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    Kid kid = new Kid(125, "Anshu", 99);
    //kid moved from Transient state to Persistent state
    session.save(kid);
    kid.setMarks(50);
    tx.commit();

    kid.setMarks(70);





  }


}
