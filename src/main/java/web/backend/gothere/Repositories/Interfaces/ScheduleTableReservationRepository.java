package web.backend.gothere.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;

public interface ScheduleTableReservationRepository 
    extends JpaRepository<ScheduleTableReservationEntity, Long> {
}