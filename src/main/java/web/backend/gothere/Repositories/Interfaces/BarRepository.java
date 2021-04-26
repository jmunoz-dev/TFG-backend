package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.backend.gothere.Repositories.Entities.BarEntity;

public interface BarRepository 
    extends JpaRepository<BarEntity,Long>{
   
        @Query(value = "SELECT b FROM Bars b WHERE  ((b.length < :length + 5) AND (b.length > :length - 5)) AND ((b.latitude< :latitude + 5) AND (b.latitude > :latitude - 5))")
        Collection<BarEntity> getByCoordinates(@Param("latitude")double latitude, @Param("length")double length);

   
}
