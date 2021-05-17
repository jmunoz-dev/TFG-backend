package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarImgsEntity;
import web.backend.gothere.Services.Models.BarImgsDTO;

public interface BarImgsRepository 
    extends JpaRepository<BarImgsEntity,Long>{

    Collection<BarImgsDTO> findByBar(BarEntity barEntity);
    
}