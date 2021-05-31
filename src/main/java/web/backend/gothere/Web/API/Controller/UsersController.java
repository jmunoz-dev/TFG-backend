package web.backend.gothere.Web.API.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.backend.gothere.Services.ConfirmationTokenService;
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

    private final ConfirmationTokenService confirmationTokenService;

    UsersController(UserService userService, ConfirmationTokenService confirmationTokenService){
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping(value="/{id}")
    public  UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    public UserDTO getByToken(@RequestParam("user") String token) {
        return userService.getUserByToken(token);
    }

    @PostMapping(value="/sign-up")
    public boolean createUser(@RequestBody UserDTO user, HttpServletResponse response){
        UserDTO newUser = userService.signUpUser(user);
        if(newUser != null){
            Cookie ck = new Cookie("Login", confirmationTokenService.findConfirmationTokenByUser(newUser));
            ck.setMaxAge(60 * 60 * 24 * 365 * 10);
            response.addCookie(ck);
        }
        return true;
     
    }
    @PutMapping(value="/edit")
    public UserDTO editUser(@RequestParam("token") String token,@RequestBody UserDTO user, HttpServletResponse response){
        if(confirmationTokenService.findConfirmationTokenByToken(token).isPresent()){
            Long userId = confirmationTokenService.findConfirmationTokenByToken(token).get().getUser().getIdUser();
            return userService.updateUser(userId, user);
        }
        return null;
    }

    
    @PostMapping(value="/reset-email")
    public void sendEmailReset(@RequestParam("email") String email){
        userService.sendPasswordReset(email);
    }

    @PostMapping(value="/sign-in")
    public Cookie loginUser(@RequestBody UserDTO user, HttpServletResponse response){
        UserDTO userToLog = userService.signInUser(user);
        if(userToLog != null){
            Cookie ck = new Cookie("Login", confirmationTokenService.findConfirmationTokenByUser(userToLog));
            ck.setMaxAge(60 * 60 * 24 * 365 * 10);
            ck.setPath("/");
            response.addCookie(ck);
            return ck;
        }
        return null;
    }
    
    @PostMapping(value="/sign-in-bar")
    public boolean loginBarUser(@RequestBody UserDTO user, HttpServletResponse response){
        UserDTO userToLog = userService.signInUser(user);
        if(userToLog != null){
            Cookie ck = new Cookie("adminlogin", confirmationTokenService.findConfirmationTokenByUser(userToLog));
            ck.setMaxAge(60 * 60 * 24 * 365 * 10);
            ck.setPath("/admin");
            response.addCookie(ck);
            return true;
        }
        return false;
    }
    @PostMapping(value="/sign-up-bar")
    public boolean createBarUser(@RequestBody UserDTO user, HttpServletResponse response){
        UserDTO userToLog = userService.signUpUser(user);
        if(userToLog != null){
            Cookie ck = new Cookie("adminlogin", confirmationTokenService.findConfirmationTokenByUser(userToLog));
            ck.setMaxAge(60 * 60 * 24 * 365 * 10);
            ck.setPath("/admin");
            response.addCookie(ck);
            return true;
        }
        return false;
    }
}
