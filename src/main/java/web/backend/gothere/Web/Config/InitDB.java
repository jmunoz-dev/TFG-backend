package web.backend.gothere.Web.Config;

import java.time.LocalDate;

import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;
import web.backend.gothere.Repositories.Interfaces.UserOfferRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDataBase(OffersRepository offerR, UserOfferRepository userofferR, UserRepository userR){
        return args -> {
            offerR.save(new OfferEntity("oferta de prueba", "dos jarricas", "hola.jpg", 2.3, 23, 23,  LocalDate.now(), LocalDate.now()));
            offerR.save(new OfferEntity("oferta de prueba 2", "holas", "hola.jpg", 2.3, 23, 23, LocalDate.now(), LocalDate.now()));
            userR.save(new UserEntity("holsda@gmail.com", "ruben", "valero", "1234","12345667"));
            userR.save(new UserEntity("hola@gmail.com", "ruben", "valero", "1234","234123412"));

        };
    }
}
