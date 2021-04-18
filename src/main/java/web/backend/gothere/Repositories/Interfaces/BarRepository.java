package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.BarEntity;

public interface BarRepository 
    extends JpaRepository<BarEntity,Long>{
   
    

   
}
