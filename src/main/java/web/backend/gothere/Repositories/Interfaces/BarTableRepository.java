package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarTableEntity;

public interface BarTableRepository 
    extends JpaRepository<BarTableEntity,Long>{
    
}
