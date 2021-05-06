package web.backend.gothere.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;
import web.backend.gothere.Services.Models.ReservationBookDTO;
import web.backend.gothere.Services.Models.UserDTO;

public class ReservationBookService {
    @Autowired
    private ReservationBookRepository reservationBookRepository;

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
        List<ReservationBookDTO> currentReservations = new ArrayList<>();
        for(int i = 0 ; i < reservationList.size(); i++){
            if(reservationList.get(i).getCheckOutHour().isBefore(LocalDateTime.now())){
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


}
