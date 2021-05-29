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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.OffersService;
import web.backend.gothere.Services.Models.BarDTO;
import web.backend.gothere.Services.Models.OfferDTO;

@RestController
@RequestMapping("api/offers")
public class OffersController {
    private final OffersService offersService;
    private final BarService barService;

    OffersController(OffersService offersService, BarService barService) {
        this.offersService = offersService;
        this.barService = barService;
    }

    @GetMapping()
    public List<OfferDTO> getAllOffers(@RequestParam(required = false, name = "latitude") Double latitude,
            @RequestParam(required = false, name = "length") Double length,
            @RequestParam(required = false, name = "distance") Double distance) throws ResponseStatusException {

        if (!offersService.getAll(latitude, length, distance).isEmpty()) {
            return offersService.getAll(latitude, length, distance);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public OfferDTO getOffersById(@PathVariable("id") Long id) throws ResponseStatusException {
        return offersService.findbyOfferId(id);
    }

    @GetMapping("/bar/{id}")
    public List<OfferDTO> getBarOffersById(@PathVariable("id") Long id) throws ResponseStatusException {
        return offersService.findOffersByBarId(id);
    }

    @PostMapping()
    public Long addOffer(@RequestBody OfferDTO offer) {
        try {
            BarDTO bar = barService.getBarById(offer.getBar().getIdbar());
            OfferDTO offerToAdd = offer;
            offerToAdd.setBar(bar);
            OfferDTO response = offersService.add(offerToAdd);
            return response.getIdOffer();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOffer(@PathVariable Long id, @RequestBody OfferDTO offer) {
        try {
            offersService.update(id, offer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteOffer(@PathVariable("id") Long id) {
        try {
            offersService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
