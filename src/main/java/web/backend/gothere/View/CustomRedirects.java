package web.backend.gothere.View;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomRedirects {

    @GetMapping("/api")
    public String redirectToHome() {
        return "redirect:/swagger-ui/";
    }

}
