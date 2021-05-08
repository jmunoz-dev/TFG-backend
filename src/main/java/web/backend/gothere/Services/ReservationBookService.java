package web.backend.gothere.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleTableReservationRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;
import web.backend.gothere.Services.Models.ReservationBookDTO;
import web.backend.gothere.Services.Models.UserDTO;

public class ReservationBookService {
    @Autowired
    private ReservationBookRepository reservationBookRepository;
    @Autowired
    private ScheduleTableReservationRepository scheduleTableReservationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationBookDTO> getAll() throws ResponseStatusException {
        
        List<ReservationBookDTO> reservationList = reservationBookRepository.findAll().stream()
        .map(x -> modelMapper.map(x, ReservationBookDTO.class))
        .collect(Collectors.toList());

        if(!reservationList.isEmpty()){
            return reservationList;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    // find user reservations 
    public List<ReservationBookDTO> getByUser(Long idUser, boolean currentReservation) throws ResponseStatusException {
        Optional<UserEntity> user = userRepository.findById(idUser);
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not foud");
        }

        List<ReservationBookDTO> reservationList = reservationBookRepository.findByUser(user.get()).stream()
        .map(x -> modelMapper.map(x, ReservationBookDTO.class))
        .collect(Collectors.toList());
        
        if(reservationList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding data");
        }
        if(!currentReservation){
            return reservationList;
        }
        //listado de solo las que aún no han pasado
        List<ReservationBookDTO> currentReservations = new ArrayList<>();
        for(int i = 0 ; i < reservationList.size(); i++){
            if(reservationList.get(i).getReservationDate().isAfter(LocalDate.now().minus(1, ChronoUnit.DAYS))){
                currentReservations.add(reservationList.get(i));
            }
        }
        return currentReservations;

    }

    public ReservationBookDTO getById(Long reBoId){
        Optional<ReservationBookEntity> resevation =  reservationBookRepository.findById(reBoId);
        if(resevation.isPresent()){
            return modelMapper.map(resevation, ReservationBookDTO.class);
        }
        throw new ElementNotFoundException();
    }

    public void setCanceled(Long reBoId){
        Optional<ReservationBookEntity> resevation =  reservationBookRepository.findById(reBoId);
        if(resevation.isPresent()){
            ReservationBookEntity temp = resevation.get();
            temp.setCanceled(true);
            reservationBookRepository.save(temp);
        }
        throw new ElementNotFoundException();
    }

    public Long add(ReservationBookDTO reservation){
        //buscamos el usuario y lo seteamos
        Long idUser = reservation.getUser().getIdUser();
        Optional<UserEntity> user = userRepository.findById(idUser);
        if(!user.isPresent()){
            throw new ElementNotFoundException();
        }
        reservation.setUser(modelMapper.map(user, UserDTO.class));

        //buscamos el horario de la mesa
        Optional<ScheduleTableReservationEntity> scheduletable = scheduleTableReservationRepository.findById(reservation.getScheduleTableReservation().getIdScheduleTableReservation());
        if(!scheduletable.isPresent()){
            throw new ElementNotFoundException();
        }
        
        //comprobamos que esa reserva no haya hecho
        Collection<ReservationBookEntity> reservasConEseHorarioMesa = reservationBookRepository.findByReservationDateAndScheduleTableReservation(reservation.getReservationDate(), scheduletable.get());
        if(!reservasConEseHorarioMesa.isEmpty()){
            throw new ElementNotFoundException();
        }
        //insertamos la nueva reserva
        ReservationBookEntity entityToInsert = modelMapper.map(reservation, ReservationBookEntity.class);
       return  modelMapper.map(reservationBookRepository.save(entityToInsert), ReservationBookDTO.class).getIdReservationBook();
        
    }
}
