package web.backend.gothere.Repositories.Interfaces;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import web.backend.gothere.Repositories.Entities.TableEntity;
import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;

public interface ScheduleTableReservationRepository 
    extends JpaRepository<ScheduleTableReservationEntity, Long> {
        Collection<TableEntity> findByTable(TableEntity barEntity);

}
