package web.backend.gothere.Web.API.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.backend.gothere.Services.UserOffersService;
import web.backend.gothere.Services.Models.UserOfferDTO;

@RestController
@RequestMapping("api/userOffers")
public class UserOffersController {
    
    private final UserOffersService userOffersService;

    public UserOffersController(UserOffersService userOffersService) {
        this.userOffersService = userOffersService;
    }

    @PostMapping()
    public UserOfferDTO AddUserOffer(@RequestBody UserOfferDTO userOffer){
        return userOffersService.add(userOffer);
    }
    @GetMapping()
    public List<UserOfferDTO> GetAll(){
        return userOffersService.getAll();
    }
    

}
