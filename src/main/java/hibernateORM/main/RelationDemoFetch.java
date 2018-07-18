package hibernateORM.main;

import hibernateORM.entity.Laptop;
import hibernateORM.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelationDemoFetch {
  private static Logger logger = LoggerFactory.getLogger(RelationDemoFetch.class);

  public static void main(String[] args) {

    Configuration cfg = new Configuration().configure()
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Laptop.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();

    //Fetch
    Student myStudent = session.get(Student.class, 1234);
    logger.info("{}", myStudent);
    tx.commit();
  }


}
