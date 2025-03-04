package food_store.example.foodstore.service;

import food_store.example.foodstore.model.Role;

public interface RoleService {

    void addRole(Role role);

    Role findRoleByName(String name);
}
