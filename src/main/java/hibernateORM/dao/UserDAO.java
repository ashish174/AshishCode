package hibernateORM.dao;

import hibernateORM.HibernateDBUtil;
import hibernateORM.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;

public class UserDAO {
  Logger logger = org.slf4j.LoggerFactory.getLogger(UserDAO.class);
  Session session;
  public User getUser(String userName){
    User user;
    session = HibernateDBUtil.getSession();
    Transaction tx = session.beginTransaction();
    user = session.get(User.class, userName);
    tx.commit();
    logger.info("{}", user);
    return user;
  }

  public void saveUser(User user){
    session = HibernateDBUtil.getSession();
    Transaction tx = session.beginTransaction();
    logger.info("Row Saved : {}", user);
    session.save(user);
    tx.commit();
  }
}
