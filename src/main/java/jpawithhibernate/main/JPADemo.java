package jpawithhibernate.main;

import hibernateORM.entity.Kid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPADemo {
  private static Logger logger = LoggerFactory.getLogger(JPADemo.class);


  public static void main(String[] args) {
    Kid kid1 = new Kid(129, "Pihu", 80);
    Kid kid2 = new Kid(130, "Riya", 70);

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AshishDB");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(kid1);
    entityManager.persist(kid2);

    Kid kid3 = entityManager.find(Kid.class, 124);
    logger.info("{}", kid3);
    entityManager.getTransaction().commit();
  }
}
