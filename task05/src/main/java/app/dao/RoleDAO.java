package app.dao;

import app.model.Role;

import java.util.List;

public interface RoleDAO {

    void create(Role role);
    List<Role> readAll();
    void delete(long id);
    Role getRoleByName(String roleName);

}
