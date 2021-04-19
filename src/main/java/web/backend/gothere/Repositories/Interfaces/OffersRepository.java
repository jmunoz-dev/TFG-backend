package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import web.backend.gothere.Repositories.Entities.OfferEntity;

public interface OffersRepository 
    extends JpaRepository<OfferEntity, Long>{

        @Query(value = "SELECT * FROM Offers of JOIN Bars b ON b.id = p.idBar where b.id =:idBar")
        Collection<Long> findByBar(@Param("idBar")Long idBar);

}
