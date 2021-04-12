package web.backend.gothere.web.view;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.backend.gothere.repositories.entities.ConfirmationTokenEntity;
import web.backend.gothere.repositories.entities.UserEntity;
import web.backend.gothere.services.ConfirmationTokenService;
import web.backend.gothere.services.UserService;
import web.backend.gothere.services.models.UserDTO;


@Controller
public class UserController {
    
	private final  UserService userService;

	private  final ConfirmationTokenService confirmationTokenService;

	UserController(UserService userService, ConfirmationTokenService confirmationTokenService){
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

	// @GetMapping("/sign-in")
	// String signIn() {

	// 	return "log-in";
	// }

	// @GetMapping("/sign-up")
	// String signUp() {

	// 	return "sign-up";
	// }
	// @GetMapping("/logged")
	// String logged() {

	// 	return "logged";
	// }


	// @PostMapping("/sign-up")
	// String signUp(UserDTO user) {

	// 	userService.signUpUser(user);

	// 	return "redirect:/sign-in";
	// }

	// @PostMapping("/sign-in")
	// String signIn(UserEntity user) {
	// 	if (userService.signInUser(user)){
	// 		return "redirect:/logged";
	// 	}
	// 	return "redirect:/sign-in";
		
	// }

	@GetMapping("/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationTokenEntity> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

		optionalConfirmationToken.ifPresent(userService::confirmUser);

		return "/logged";
	}
}
