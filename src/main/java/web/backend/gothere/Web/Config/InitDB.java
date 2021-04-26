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
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDataBase(OffersRepository offerR, UserOfferRepository userofferR, UserRepository userR, BarRepository barR){
        return args -> {
            barR.save(new BarEntity("name", "description", "66666666", "direccion", 45.4, 55.69, "schedule", 25, 90, 80));
            barR.save(new BarEntity("name 2", "description", "66666666", "direccion", 45.4, 55.69, "schedule", 25, 90, 80));

            offerR.save(new OfferEntity("oferta de prueba", "dos jarricas", "hola.jpg", 2.3, 23, 23,  LocalDate.now(), LocalDate.now(), barR.findById(1L).get() ));
            offerR.save(new OfferEntity("oferta de prueba 2", "holas", "hola.jpg", 2.3, 23, 23, LocalDate.now(), LocalDate.now(),  barR.findById(2L).get() ));
            offerR.save(new OfferEntity("titulo oferta ", "desciption", "img.png", 22.50, 15, 0, LocalDate.of(2021,05,30), LocalDate.of(2022, 01, 01), barR.findById(1L).get() ));

            userR.save(new UserEntity("holsda@gmail.com", "ruben", "valero", "1234","12345667"));
            userR.save(new UserEntity("hola@gmail.com", "ruben", "valero", "1234","234123412"));

        };
    }
}
