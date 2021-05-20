package web.backend.gothere.Web.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.UserService;
import web.backend.gothere.Services.Models.UserDTO;

@Controller
@RequestMapping("admin")
public class AdminViewController {
    
    private final UserService userService;
    private final BarService barService;
    

    public AdminViewController(UserService userService, BarService barService) {
        this.userService = userService;
        this.barService = barService;
    }

    @GetMapping("")
    public ModelAndView barLoginPage(){
        //TODO mirar que esté la cookie
        ModelAndView mv = new ModelAndView("login");
        UserDTO user = new UserDTO();
        mv.addObject("user", user);
        mv.addObject("bar", barService.getBarById(3L));
        return mv;
    } 

    @GetMapping("/home")
    public ModelAndView barHomePage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        if(cookie == null){
            ModelAndView mv2 = new ModelAndView("redirect:/admin");
            return mv2;
        }
        ModelAndView mv = new ModelAndView("admin_home");
    @GetMapping("/tables")
    public ModelAndView barTablesPage(@CookieValue( required = false, value="adminlogin") String cookie ){
        
        ModelAndView mv2 = new ModelAndView("redirect:/admin");
        if(cookie == null){
            return mv2;
        }
        if(!isBarOwner(cookie)){
            return mv2;
        }
    
        ModelAndView mv = new ModelAndView("admin_offers");
       
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
