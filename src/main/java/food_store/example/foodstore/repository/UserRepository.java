package food_store.example.foodstore.repository;

import food_store.example.foodstore.constant.ProviderEnum;
import food_store.example.foodstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndProvider(String email, ProviderEnum provider);
    boolean existsByEmailAndProvider(String email, ProviderEnum provider);
    User findByCodeAndProvider(String code, ProviderEnum provider);
}
