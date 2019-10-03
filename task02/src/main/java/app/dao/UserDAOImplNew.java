package app.dao;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class UserDAOImplNew implements UserDAO {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setSessionFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("FROM User").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User userFromDB = entityManager.find(User.class, user.getId());
        entityManager.getTransaction().begin();
        userFromDB.setLogin(user.getLogin());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setName(user.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User userFromDB = entityManager.find(User.class, id);
        if (userFromDB != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(userFromDB);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public User getById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User userFromDB = entityManager.find(User.class, id);
        entityManager.close();
        return userFromDB;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("FROM User WHERE login=:login AND password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }
}
