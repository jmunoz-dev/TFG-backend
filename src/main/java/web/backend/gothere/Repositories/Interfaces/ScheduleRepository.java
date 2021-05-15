package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ScheduleEntity;

public interface ScheduleRepository 
    extends JpaRepository<ScheduleEntity, Long> {

    }
