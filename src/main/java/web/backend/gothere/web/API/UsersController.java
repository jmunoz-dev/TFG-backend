package web.backend.gothere.web.API;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.backend.gothere.services.UserService;
import web.backend.gothere.services.models.UserDTO;
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
    public UserDTO createUser(@RequestBody UserDTO user){
        return  userService.signUpUser(user);
     
    }

    
}
