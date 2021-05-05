package web.backend.gothere.Repositories.Interfaces;


import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ReservationBookEntity;

public interface ReservationBookRepository 
    extends JpaRepository<ReservationBookEntity, Long> {
    
}
