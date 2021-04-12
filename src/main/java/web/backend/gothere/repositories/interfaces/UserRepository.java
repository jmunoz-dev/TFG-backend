package web.backend.gothere.repositories.interfaces;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.repositories.entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
    
}
