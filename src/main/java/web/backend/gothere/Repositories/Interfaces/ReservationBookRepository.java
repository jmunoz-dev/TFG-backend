package web.backend.gothere.Repositories.Interfaces;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;

public interface ReservationBookRepository 
    extends JpaRepository<ReservationBookEntity, Long> {
    
    Collection<ReservationBookEntity> findByUser(UserEntity user);
    Optional<ReservationBookEntity> findById(Long id);
}
