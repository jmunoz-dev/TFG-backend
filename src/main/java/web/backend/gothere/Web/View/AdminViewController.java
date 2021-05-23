package web.backend.gothere.Web.View;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.backend.gothere.Repositories.Entities.UserRole;
import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.BarTableService;
import web.backend.gothere.Services.OffersService;
import web.backend.gothere.Services.ReservationBookService;
import web.backend.gothere.Services.UserService;
import web.backend.gothere.Services.Models.BarTableDTO;
import web.backend.gothere.Services.Models.OfferDTO;
import web.backend.gothere.Services.Models.ReservationBookDTO;
import web.backend.gothere.Services.Models.UserDTO;

@Controller
@RequestMapping("admin")
public class AdminViewController {
    
    private final UserService userService;
    private final BarService barService;
    private final OffersService offerService;
    private final ReservationBookService reservationBookService;
    private final BarTableService barTableService;
    

    public AdminViewController(UserService userService, BarService barService, OffersService offerService, ReservationBookService reservationBookService, BarTableService barTableService) {
        this.userService = userService;
        this.barService = barService;
        this.offerService = offerService;
        this.reservationBookService = reservationBookService;
        this.barTableService = barTableService;
    }

    @GetMapping("")
    public ModelAndView barLoginPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin/home");
        
        if(cookie != null && isBarOwner(cookie)){
            return mv2;
        }
      
        ModelAndView mv = new ModelAndView("admin/login");
        return mv;
    } 

    @GetMapping("/home")
    public ModelAndView barHomePage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        ModelAndView mv = new ModelAndView("admin/admin_home");
       
       UserDTO user = userService.getUserByToken(cookie);
       
    
        mv.addObject("bar", barService.getBarById(user.getIdBar()));

        return mv;
    }

    @GetMapping("/offers")
    public ModelAndView barOffersPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        UserDTO user = userService.getUserByToken(cookie);
        
        ModelAndView mv = new ModelAndView("admin/offers");
        List<OfferDTO> offers = offerService.findOffersByBarId(user.getIdBar());
        mv.addObject("offers",offers);

        return mv;
    }

    @GetMapping("/reservations")
    public ModelAndView barReservationsPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        
        ModelAndView mv = new ModelAndView("admin/reservations");
       
       UserDTO user = userService.getUserByToken(cookie);
       
       
        List<ReservationBookDTO> reservations = reservationBookService.getByBar(user.getIdBar());
        mv.addObject("reservations",reservations);

        return mv;
    }

    @GetMapping("/tables")
    public ModelAndView barTablesPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
    
        ModelAndView mv = new ModelAndView("admin/tables");
       
       UserDTO user = userService.getUserByToken(cookie);
       
        List<BarTableDTO> barTables = barTableService.getByBarId(user.getIdBar());
        mv.addObject("barTables",barTables);

        return mv;
    }

    private boolean isBarOwner(String cookie){
        
        UserDTO user = userService.getUserByToken(cookie);
        return user.getUserRole().equals(UserRole.BAR.toString());
        
    }

      

}
