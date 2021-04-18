package web.backend.gothere.Web.Config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDatabase(OffersRepository offersRepository) {
        return args -> {
            for(int i = 1; i<=10; i++){   
                offersRepository.save(new OfferEntity("titulo oferta", "desciption", "img.png", 22.50, 15, 0, LocalDate.now(), LocalDate.of(2022, 01, 01), new BarEntity(45L, 5569L, 5569L, "name", "description", "direction", "66666666", "schedule", 25, 90, 80)));
            }            
        };
    }
}
