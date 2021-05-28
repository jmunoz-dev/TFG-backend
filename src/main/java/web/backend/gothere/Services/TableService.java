package web.backend.gothere.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.TableEntity;
import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Entities.ScheduleEntity;
import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.TableRepository;
import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleTableReservationRepository;
import web.backend.gothere.Services.Models.TableDTO;
import web.backend.gothere.Services.Models.ReservationBookDTO;
import web.backend.gothere.Services.Models.ScheduleTableReservationDTO;

public class TableService {
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private ReservationBookRepository reservationBookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BarRepository barRepository;
    
    public List<TableDTO> getAll() {
        List<TableDTO> tables =  tableRepository.findAll().stream().map(x -> modelMapper.map(x, TableDTO.class))
                .collect(Collectors.toList());
        return  deleteScheduleTable(tables);        
    }
    public TableDTO getById(Long id) {
        Optional<TableEntity> result = tableRepository.findById(id);
        if (result.isPresent()) {
            return modelMapper.map(result, TableDTO.class);
        }
        return null;
    }

    public TableDTO add(TableDTO table) {
        TableEntity entityToUpdate = modelMapper.map(table, TableEntity.class);
        Optional<BarEntity> bar =barRepository.findById(table.getBar().getIdbar());
        if(!bar.isPresent()){
            return null;
        }
        entityToUpdate.setBar(bar.get());
       
        TableEntity result = tableRepository.save(entityToUpdate);
        return modelMapper.map(result, TableDTO.class);
    }

    public TableDTO update(Long id, TableDTO table) {
        Optional<TableEntity> dataToUpdate = tableRepository.findById(id);
        if (dataToUpdate.isPresent()) {

            TableEntity entityToUpdate = modelMapper.map(table, TableEntity.class);
            entityToUpdate.setIdTable(id);
            TableEntity result = tableRepository.save(entityToUpdate);
            return modelMapper.map(result, TableDTO.class);

        }
        return null;
    }

    public void delete(Long idTable) {
        Optional<TableEntity> entityToDelete = tableRepository.findById(idTable);
        if (entityToDelete.isPresent())
            tableRepository.delete(entityToDelete.get());
    }
    public List<TableDTO> getTableByBarAndAvailabilityDate(Long idBar, LocalDate date){
        Optional<BarEntity> bar = barRepository.findById(idBar);
        if(!bar.isPresent()){
            throw new ElementNotFoundException();
        }
        
        List<TableDTO> tables = getByBarId(idBar);

        //recorro la lista de mesas para comprobar si están libres
        for(int i = 0 ; i < tables.size(); i++){
            Optional<TableEntity> table = tableRepository.findById(tables.get(i).getIdTable());
            TableDTO actualTable = tables.get(i);
            if(!table.isPresent()){
                break;
            }
            //recogemos las reservas por fecha y mesa
            List<ReservationBookDTO> reservationBooks = reservationBookRepository.findByReservationDateAndScheduleTableReservationTable(date, table.get())
            .stream().map(x -> modelMapper.map(x, ReservationBookDTO.class))
            .collect(Collectors.toList());
            //si no hay reservas salimos
            if(reservationBooks.isEmpty()){
                break;
            }
            //recorremos las reservas para ver que horarios están cogidos
            for(int j = 0; j < reservationBooks.size(); j++){
                if(reservationBooks.get(j).isCanceled()){
                    break;
                }
                for(int k = 0; k < actualTable.getScheduleTableReservations().size(); k++){
                    //si el horario de la reserva coincide eliminamos ese horario de la mesa
                    if(reservationBooks.get(j).getScheduleTableReservation().getIdScheduleTableReservation().equals(actualTable.getScheduleTableReservations().get(k).getIdScheduleTableReservation())){
                        actualTable.getScheduleTableReservations().remove(k);
                    }
                }
            }
            if(actualTable.getScheduleTableReservations().isEmpty()){
                actualTable.setReservated(true);
            }
        }

        return tables;
    }
    public List<TableDTO> getByBarId(Long idBar){
        Optional<BarEntity> bar = barRepository.findById(idBar);
        if(!bar.isPresent()){
            throw new ElementNotFoundException();
        }
        
        List<TableDTO> tables =  tableRepository.findByBar(bar.get()).stream().map(x -> modelMapper.map(x, TableDTO.class))
        .collect(Collectors.toList());
        return  deleteScheduleTable(tables);

    }
    private List<TableDTO> deleteScheduleTable(List<TableDTO> tables){
        for(TableDTO table : tables){
            List<ScheduleTableReservationDTO> schedules = table.getScheduleTableReservations();
            for(ScheduleTableReservationDTO schedule : schedules){
                 schedule.setTable(null);
            }
         }
         return tables;
    }

}
