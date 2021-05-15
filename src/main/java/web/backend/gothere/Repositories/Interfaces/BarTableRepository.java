package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarTableEntity;

public interface BarTableRepository 
    extends JpaRepository<BarTableEntity,Long>{
    
        Collection<BarTableEntity> findByBar(BarEntity barEntity);
}
