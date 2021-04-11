package web.backend.Web.API.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import web.backend.Services.OffersService;
import web.backend.Services.Models.OfferDTO;

@RestController
@RequestMapping("api/offers")
public class OffersController {
    private final OffersService offersService;

    OffersController (OffersService offersService) {
        this.offersService = offersService;
    }

    
    @GetMapping()
    public List<OfferDTO> GetAllOffers() throws ResponseStatusException {
        if(!offersService.getAll().isEmpty()){
            return offersService.getAll();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public OfferDTO GetOffersById(@PathVariable("id") Long id) throws ResponseStatusException {
        return offersService.findbyOfferId(id);
    }

    @GetMapping("/bar/{id}")
    public OfferDTO GetBarOffersById(@PathVariable("id") Long id) throws ResponseStatusException {
       // Esto se tiene que revisar porque es un copy/paste
        return offersService.findbyOfferId(id);
    }

}
