package web.backend.gothere.Web.View;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Services.ConfirmationTokenService;
import web.backend.gothere.Services.UserService;


@Controller
public class UserController {
    
	private final  UserService userService;

	private  final ConfirmationTokenService confirmationTokenService;

	UserController(UserService userService, ConfirmationTokenService confirmationTokenService){
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

	@GetMapping("/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationTokenEntity> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

		optionalConfirmationToken.ifPresent(userService::confirmUser);

		return "/logged";
	}
	@GetMapping("/reset-password/{token}")
	public ModelAndView resetEmail(@PathVariable("token") String token) {
		ModelAndView mv = new ModelAndView("reset-password");
		Optional<ConfirmationTokenEntity> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
		if(optionalConfirmationToken.isPresent()){
			mv.addObject("token", confirmationTokenService.findConfirmationTokenByToken(token).get().getConfirmationToken());
		}else{
			return null;
		}
		return mv;
	}
	@PostMapping("/reset-password")
	public String changePassword(@RequestParam("token") String token,@RequestParam("newPassword") String newPassword){
		
		if(confirmationTokenService.findConfirmationTokenByToken(token).isPresent()){
			Long idUser = confirmationTokenService.findConfirmationTokenByToken(token).get().getUser().getIdUser();
            if(userService.updatePassword(idUser, newPassword)){
				return "redirect:/reset-success";
			}
        }
        return "redirect:/error";
	}
	@GetMapping("/")
	public String index(){
		return "redirect:/swagger-ui/";
	}
}
