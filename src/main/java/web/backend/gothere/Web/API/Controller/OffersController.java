package web.backend.gothere.Web.API.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Services.OffersService;
import web.backend.gothere.Services.Models.OfferDTO;



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
    public List<OfferDTO> GetBarOffersById(@PathVariable("id") Long id) throws ResponseStatusException {
        return offersService.findOffersByBarId(id);
    }


    @PostMapping()
    public  ResponseEntity<HttpStatus> postOffer(@RequestBody OfferDTO offer) {
        try {
            offersService.add(offer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
   @PutMapping("/{id}")
   public ResponseEntity<HttpStatus> putMethodName(@PathVariable Long id, @RequestBody OfferDTO offer) {
    try {
        offersService.update(id, offer);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
   }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            offersService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   


}
