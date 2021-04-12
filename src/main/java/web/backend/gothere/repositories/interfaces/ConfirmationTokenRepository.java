package web.backend.gothere.repositories.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;


public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long>{
    Optional<ConfirmationTokenEntity> findConfirmationTokenByConfirmationToken(String token);
    
}
