package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarImgsEntity;

public interface BarImgsRepository 
    extends JpaRepository<BarImgsEntity,Long>{
    
}
