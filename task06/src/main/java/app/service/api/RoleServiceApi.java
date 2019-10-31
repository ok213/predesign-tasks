package app.service.api;

import app.model.Role;

import java.util.List;

public interface RoleServiceApi {

    List<Role> getAllRoles();
    void createRole(Role role);
    void deleteRoleById(Long id);

}
