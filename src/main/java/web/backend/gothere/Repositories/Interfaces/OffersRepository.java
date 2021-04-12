package web.backend.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.Repositories.Entities.OfferEntity;

public interface OffersRepository 
    extends JpaRepository<OfferEntity, Long>{
}
