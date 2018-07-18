package hibernateORM.main;

import hibernateORM.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceBinding;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Demo {
  private static Logger logger = LoggerFactory.getLogger(Demo.class);


  public static void main(String[] args) {
    User user = new User("Ram", "Shakti", new Date());
    Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    session.save(user);
    tx.commit();
  }


}
