package food_store.example.foodstore.service.impl;

import food_store.example.foodstore.model.Permission;
import food_store.example.foodstore.repository.PermissionRepository;
import food_store.example.foodstore.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void addPermission(Permission permission) {
        permissionRepository.save(permission);
    }
}
