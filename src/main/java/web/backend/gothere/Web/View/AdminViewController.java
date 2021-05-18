package web.backend.gothere.Web.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.backend.gothere.Services.BarService;
import web.backend.gothere.Services.UserService;

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
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("bar", barService.getBarById(3L));
        return mv;
    } 

    @GetMapping("/home")
    public ModelAndView barHomePage(){
        ModelAndView mv = new ModelAndView("admin_home");
        mv.addObject("bar", barService.getBarById(3L));
        return mv;
    } 

}
