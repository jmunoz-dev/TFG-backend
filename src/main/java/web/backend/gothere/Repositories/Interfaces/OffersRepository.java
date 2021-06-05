package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;

public interface OffersRepository extends JpaRepository<OfferEntity, Long> {

    @Query(value = "SELECT of FROM Offers of WHERE of.Bar =:idBar")
    Collection<OfferEntity> findByBar(@Param("idBar") BarEntity idBar);

}
