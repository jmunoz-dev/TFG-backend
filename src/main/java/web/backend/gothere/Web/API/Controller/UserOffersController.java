package web.backend.gothere.Web.API.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.UserOffersService;
import web.backend.gothere.Services.Models.OfferDTO;
import web.backend.gothere.Services.Models.SimpleUserOfferDTO;
import web.backend.gothere.Services.Models.UserOfferDTO;

@RestController
@RequestMapping("api/userOffers")
public class UserOffersController {
    
    private final UserOffersService userOffersService;

    public UserOffersController(UserOffersService userOffersService) {
        this.userOffersService = userOffersService;
    }

    @PostMapping()
    public String AddUserOffer(@RequestBody UserOfferDTO userOffer){
        return userOffersService.add(userOffer);
    }
    @GetMapping()
    public List<UserOfferDTO> GetAll(){
        return userOffersService.getAll();
    }
    @DeleteMapping
    public void deleteUserOffer(@RequestParam String code){
        userOffersService.deleteUserOffer(code);
    }
    @PutMapping("/{code}")
    public OfferDTO SetOfferUsed(@PathVariable("code") String code){
       return userOffersService.setOfferUsed(code);
    }
    @GetMapping("/bar")
    public List<SimpleUserOfferDTO> getByUserAndBar(@RequestParam Long idBar,@RequestParam Long idUser){
        return userOffersService.getByUserAndBar(idBar, idUser);
    }

}
