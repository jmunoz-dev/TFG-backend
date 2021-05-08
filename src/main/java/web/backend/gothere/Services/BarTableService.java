package web.backend.gothere.Services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarTableEntity;
import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.BarTableRepository;
import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Services.Models.BarTableDTO;
import web.backend.gothere.Services.Models.ReservationBookDTO;

public class BarTableService {
    @Autowired
    private BarTableRepository barTableRepository;
    @Autowired
    private ReservationBookRepository reservationBookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BarRepository barRepository;

    public List<BarTableDTO> getAll() {
        return barTableRepository.findAll().stream().map(x -> modelMapper.map(x, BarTableDTO.class))
                .collect(Collectors.toList());
    }

    public BarTableDTO add(BarTableDTO barTable) {
        BarTableEntity entityToUpdate = modelMapper.map(barTable, BarTableEntity.class);
        BarTableEntity result = barTableRepository.save(entityToUpdate);
        return modelMapper.map(result, BarTableDTO.class);
    }

    public BarTableDTO update(Long id, BarTableDTO barTable) {
        Optional<BarTableEntity> dataToUpdate = barTableRepository.findById(id);
        if (dataToUpdate.isPresent()) {

            BarTableEntity entityToUpdate = modelMapper.map(barTable, BarTableEntity.class);
            entityToUpdate.setIdBarTable(id);
            BarTableEntity result = barTableRepository.save(entityToUpdate);
            return modelMapper.map(result, BarTableDTO.class);

        }
        return null;
    }

    public void delete(Long idBarTable) {
        Optional<BarTableEntity> entityToDelete = barTableRepository.findById(idBarTable);
        if (entityToDelete.isPresent())
            barTableRepository.delete(entityToDelete.get());
    }
    public List<BarTableDTO> getTableByBarAndAvailabilityDate(Long idBar, LocalDate date){
        Optional<BarEntity> bar = barRepository.findById(idBar);
        if(!bar.isPresent()){
            throw new ElementNotFoundException();
        }
        
        List<BarTableDTO> barTables =  barTableRepository.findByBar(bar.get()).stream().map(x -> modelMapper.map(x, BarTableDTO.class))
        .collect(Collectors.toList());

        //recorro la lista de mesas para comprobar si están libres
        for(int i = 0 ; i < barTables.size(); i++){
            Optional<BarTableEntity> table = barTableRepository.findById(barTables.get(i).getIdBarTable());
            BarTableDTO actualTable = barTables.get(i);
            if(!table.isPresent()){
                break;
            }
            //recogemos las reservas por fecha y mesa
            List<ReservationBookDTO> reservationBooks = reservationBookRepository.findByReservationDateAndScheduleTableReservationBarTable(date, table.get())
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

        return barTables;
    }

}
