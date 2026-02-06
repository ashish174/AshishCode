package database.hibernateORM.main;

import database.hibernateORM.entity.Laptop;
import database.hibernateORM.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RelationDemo {
  private static Logger logger = LoggerFactory.getLogger(RelationDemo.class);

  public static void main(String[] args) {
    Student student = new Student(1234, "Ashish", 100);
    Laptop laptop = new Laptop(10, "Lenovo");
    Laptop laptop1 = new Laptop(11, "Casio");
    student.getLaptops().add(laptop);
    student.getLaptops().add(laptop1);
    laptop.setStudent(student);
    laptop1.setStudent(student);

    Configuration cfg = new Configuration().configure()
        .addAnnotatedClass(Student.class)
        .addAnnotatedClass(Laptop.class);
//    ServiceRegistry reg = new Se

    SessionFactory sf = cfg.buildSessionFactory();
    Session session = sf.openSession();

    Transaction tx = session.beginTransaction();
    session.save(student);
    session.save(laptop);
    session.save(laptop1);
    tx.commit();
  }


}
