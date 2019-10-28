package app.dao;

import app.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> readAll() {
        return entityManager.createQuery("FROM Role").getResultList();
    }

    @Override
    public void delete(long id) {
        Role roleFromDB = entityManager.find(Role.class, id);
        if (roleFromDB != null) {
            entityManager.remove(roleFromDB);
        }
    }
}
