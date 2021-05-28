package web.backend.gothere.Services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Entities.ScheduleEntity;
import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;
import web.backend.gothere.Repositories.Entities.TableEntity;
import web.backend.gothere.Repositories.Interfaces.ScheduleRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleTableReservationRepository;
import web.backend.gothere.Repositories.Interfaces.TableRepository;
import web.backend.gothere.Services.Models.ScheduleTableReservationDTO;

public class ScheduleTableReservationService {
    
    @Autowired
    private ScheduleTableReservationRepository scheduleTableReservationRepository;
    
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ScheduleTableReservationDTO add(ScheduleTableReservationDTO schedule) {
        ScheduleTableReservationEntity entityToUpdate = modelMapper.map(schedule, ScheduleTableReservationEntity.class);
        Optional<TableEntity> table = tableRepository.findById(schedule.getTable().getIdTable());

        Optional<ScheduleEntity> sch = scheduleRepository.findById(schedule.getSchedule().getIdSchedule());
        if((!table.isPresent() || !sch.isPresent())){
            return null;
        }
        entityToUpdate.setSchedule(sch.get());
        entityToUpdate.setTable(table.get());
        ScheduleTableReservationEntity result = scheduleTableReservationRepository.save(entityToUpdate);
        return modelMapper.map(result, ScheduleTableReservationDTO.class);
    }
}
