package web.backend.gothere.Web.View;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.backend.gothere.Repositories.Entities.UserRole;
import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.TableService;
import web.backend.gothere.Services.OffersService;
import web.backend.gothere.Services.ReservationBookService;
import web.backend.gothere.Services.UserService;
import web.backend.gothere.Services.Models.TableDTO;
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
    private final TableService tableService;
    

    public AdminViewController(UserService userService, BarService barService, OffersService offerService, ReservationBookService reservationBookService, TableService tableService) {
        this.userService = userService;
        this.barService = barService;
        this.offerService = offerService;
        this.reservationBookService = reservationBookService;
        this.tableService = tableService;
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
    public ModelAndView barReservationsPage(@CookieValue( required = false, value="adminlogin") String cookie, 
        @RequestParam (required = false, value= "phone") String phone,
        @RequestParam( required = false, value="date")@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        
        ModelAndView mv = new ModelAndView("admin/reservations");
       
       UserDTO user = userService.getUserByToken(cookie);
       
        List<ReservationBookDTO> reservations = reservationBookService.getAllByBar(user.getIdBar(), phone, date);
        mv.addObject("reservations",reservations);

        return mv;
    }

    @GetMapping("/tables")
    public ModelAndView tablesPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
    
        ModelAndView mv = new ModelAndView("admin/tables");
       
       UserDTO user = userService.getUserByToken(cookie);
       
        List<TableDTO> tables = tableService.getByBarId(user.getIdBar());
        mv.addObject("tables",tables);

        return mv;
    }

    private boolean isBarOwner(String cookie){
        
        UserDTO user = userService.getUserByToken(cookie);
        return user.getUserRole().equals(UserRole.BAR.toString());
        
    }

    @GetMapping("/offers/new")
    public ModelAndView newOfferPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        UserDTO user = userService.getUserByToken(cookie);
        
        ModelAndView mv = new ModelAndView("admin/add_offer");
        mv.addObject("user",user);

        return mv;
    }
    @GetMapping("/tables/new")
    public ModelAndView newTablePage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        UserDTO user = userService.getUserByToken(cookie);
        
        ModelAndView mv = new ModelAndView("admin/add_table");
        mv.addObject("user",user);

        return mv;
    }
    @GetMapping("/reservations/new")
    public ModelAndView newReservationPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
        UserDTO user = userService.getUserByToken(cookie);
        
        ModelAndView mv = new ModelAndView("admin/add_reservations");
        mv.addObject("user",user);

        return mv;
    }

      

}
