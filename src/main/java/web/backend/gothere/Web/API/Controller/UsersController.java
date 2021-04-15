package web.backend.gothere.Web.API.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.backend.gothere.Services.UserService;
import web.backend.gothere.Services.Models.UserDTO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("api/users")
@RestController
public class UsersController {
    private final UserService userService;

    UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value="/{id}")
    public  UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
    @PostMapping(value="/sign-up")
    public UserDTO createUser(@RequestBody UserDTO user, HttpServletResponse response){
        UserDTO newUser = userService.signUpUser(user);
        Cookie ck = new Cookie("sid", newUser.getIdUser().toString());
        ck.setMaxAge(60 * 60 * 24 * 365 * 10);
        
        response.addCookie(ck);
        return  newUser;
     
    }

    
}
