package app.dao;

import app.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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

    @Override
    public Role getRoleByName(String roleName) {
        Role role = null;
        Query query = entityManager.createQuery("FROM Role WHERE role=:roleName");
        query.setParameter("roleName", roleName);
        try {
            role = (Role) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {

        }
        return role;
    }
}
