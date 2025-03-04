package food_store.example.foodstore.repository;

import food_store.example.foodstore.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
