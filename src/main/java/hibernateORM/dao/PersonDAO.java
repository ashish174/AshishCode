package hibernateORM.dao;

import hibernateORM.HibernateDBUtil;
import hibernateORM.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

public class PersonDAO {
  Logger logger = org.slf4j.LoggerFactory.getLogger(PersonDAO.class);
  Session session;


  public void savePerson(Person person){
    session = HibernateDBUtil.getSession();
    Transaction tx = session.beginTransaction();
    logger.info("Row Saved : {}", person);
    session.save(person);
    tx.commit();
  }
}
