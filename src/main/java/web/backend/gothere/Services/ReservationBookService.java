package web.backend.gothere.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Services.Models.ReservationBookDTO;

public class ReservationBookService {
    @Autowired
    private ReservationBookRepository reservationBookRepository;
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

    public ReservationBookDTO getById(Long reBoId){
        Optional<ReservationBookEntity> resevation =  reservationBookRepository.findById(reBoId);
        if(resevation.isPresent()){
            return modelMapper.map(resevation, ReservationBookDTO.class);
        }
        throw new ElementNotFoundException();
    }
}
