package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.model.Role;
import food_store.example.foodstore.repository.RoleRepository;
import food_store.example.foodstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }


}
