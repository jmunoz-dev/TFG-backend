package web.backend.gothere.Repositories.Interfaces;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
    
}
