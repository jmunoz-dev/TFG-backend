package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.OfferEntity;

public interface OffersRepository 
    extends JpaRepository<OfferEntity, Long>{
}
