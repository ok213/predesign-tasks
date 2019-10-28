package app.service;

import app.dao.RoleDAO;
import app.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void create(Role role) {
        roleDAO.create(role);
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.readAll();
    }

    @Override
    public void delete(long id) {
        roleDAO.delete(id);
    }
}
