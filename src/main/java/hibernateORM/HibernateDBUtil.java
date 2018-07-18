package hibernateORM;

import hibernateORM.entity.Laptop;
import hibernateORM.entity.Person;
import hibernateORM.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDBUtil {
  public static Session getSession(){
    Configuration cfg = new Configuration().configure()
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Laptop.class);
//    ServiceRegistry reg = new Se
    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();
    return session;
  }
}
