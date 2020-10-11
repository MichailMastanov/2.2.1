package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getU(String model, int series){
      TypedQuery<User> sql = sessionFactory.getCurrentSession().createQuery("select u from User u where u.car.model = :model AND u.car.series = :series");
      sql.setParameter("model", model);
      sql.setParameter("series", series);
      List<User> resList = sql.getResultList();
      User userRes = resList.get(0);
      sessionFactory.close();
      return userRes;
   }

}
