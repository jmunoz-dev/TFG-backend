package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;
import web.backend.gothere.Constants;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.backend.gothere.Repositories.Entities.BarEntity;

public interface BarRepository 
    extends JpaRepository<BarEntity,Long>{
   
        @Query(value = "SELECT b FROM Bars b WHERE" +
        "((b.length < :length + " +Constants.BAR_SEARCH_RADIUS +" ) AND (b.length > :length - " + Constants.BAR_SEARCH_RADIUS+"))"
        +" AND " +
        " ((b.latitude< :latitude + " +  Constants.BAR_SEARCH_RADIUS+") AND (b.latitude > :latitude - " + Constants.BAR_SEARCH_RADIUS + "))")
        Collection<BarEntity> getByCoordinates(@Param("latitude") double latitude, @Param("length") double length);

        @Query(value = "SELECT * FROM Bars b WHERE UPPER(b.name) LIKE %:name% OR UPPER(b.address) LIKE %:address% ORDER BY b.name LIMIT 10", nativeQuery = true)
        Collection<BarEntity> findByNameOrAddress(String name, String address);
}
