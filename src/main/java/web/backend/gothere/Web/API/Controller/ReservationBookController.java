package web.backend.gothere.Web.API.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.ReservationBookService;
import web.backend.gothere.Services.Models.ReservationBookDTO;

@RestController
@RequestMapping("api/reservations")
public class ReservationBookController {
    private final ReservationBookService reservationBookService;

    ReservationBookController (ReservationBookService reservationBookService) {
        this.reservationBookService = reservationBookService;
    }


    @GetMapping("/{userToken}")
    public List<ReservationBookDTO> getByUserAndBar(@PathVariable("userToken") String userToken, @RequestParam(required = false , value = "current") boolean current){
        return reservationBookService.getByUser(userToken, current);
    }

    @PostMapping("/new")
    public Long post(@RequestBody ReservationBookDTO reservation){
        return reservationBookService.add(reservation);
    }
    @PutMapping("/cancel/{idR}")
    public Long cancelReservationBook(@PathVariable Long idR){
        return reservationBookService.setCanceled(idR).getIdReservationBook();
    }
}
