package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.TableEntity;

public interface TableRepository 
    extends JpaRepository<TableEntity,Long>{
    
        Collection<TableEntity> findByBar(BarEntity barEntity);
}
