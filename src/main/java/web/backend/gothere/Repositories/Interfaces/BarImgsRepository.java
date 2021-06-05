package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarImgsEntity;

public interface BarImgsRepository 
    extends JpaRepository<BarImgsEntity,Long>{

    Collection<BarImgsEntity> findByBar(BarEntity barEntity);
    
}
