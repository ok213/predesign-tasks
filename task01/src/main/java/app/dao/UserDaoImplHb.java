package app.dao;

import app.model.User;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoImplHb implements UserDao{

    private Session session;

    public UserDaoImplHb(Session session) {
        this.session = session;
    }

    @Override
    public void create(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> readAll() {
        Transaction transaction = session.beginTransaction();
        // language=MYSQL
        List<User> listUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return listUsers;
    }

    @Override
    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        User userOriginal = (User) session.load(User.class, user.getId());
        transaction.commit();
        userOriginal.setLogin(user.getLogin());
        userOriginal.setPassword(user.getPassword());
        userOriginal.setName(user.getName());
        transaction = session.beginTransaction();
        session.update(userOriginal);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Transaction transaction = session.beginTransaction();
        // language=MYSQL
        Query<User> query = session.createQuery("DELETE FROM User WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getById(long id) {
        User user = null;
        Transaction transaction = session.beginTransaction();
        // language=MYSQL
        Query<User> query = session.createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);
        try {
            user = query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        User user = null;
        Transaction transaction = session.beginTransaction();
        // language=MYSQL
        Query<User> query = session.createQuery("FROM User WHERE login=:login AND password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        try {
            user = query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        transaction.commit();
        session.close();
        return user;
    }
}
