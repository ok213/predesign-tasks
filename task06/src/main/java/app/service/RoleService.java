package app.service;

import app.model.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);
    List<Role> getAll();
    void delete(Long id);

}
