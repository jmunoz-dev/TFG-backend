package web.backend.gothere.Web.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.backend.gothere.Services.BarService;

@Configuration
public class DI {
    @Bean
    BarService createBarService(){
        return new BarService();
    }
   

    @Bean
    ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
