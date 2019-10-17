package app.dao;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> readAll() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void update(User user) {
        User userFromDB = entityManager.find(User.class, user.getId());
        userFromDB.setLogin(user.getLogin());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setName(user.getName());
        entityManager.merge(userFromDB);
    }

    @Override
    public void delete(long id) {
        User userFromDB = entityManager.find(User.class, id);
        if (userFromDB != null) {
            entityManager.remove(userFromDB);
        }
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        User user = null;
        Query query = entityManager.createQuery("FROM User WHERE login=:login");
        query.setParameter("login", login);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        return user;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        Query query = entityManager.createQuery("FROM User WHERE login=:login AND password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        return user;
    }
}
