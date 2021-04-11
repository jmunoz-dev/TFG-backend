package web.backend.Web.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import web.backend.Services.OffersService;

public class DI {
    @Bean
    OffersService createOffersService(){
        return new OffersService();
    }

    @Bean
    ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
