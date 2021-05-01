package web.backend.gothere.Repositories.Interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;


public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long>{
    Optional<ConfirmationTokenEntity> findConfirmationTokenByConfirmationToken(String token);
    Optional<ConfirmationTokenEntity> findConfirmationTokenByUser(UserEntity user);
    
}
