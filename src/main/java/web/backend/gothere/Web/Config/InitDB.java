package web.backend.gothere.Web.Config;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.TableEntity;
import web.backend.gothere.Repositories.Entities.ConfirmationTokenEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Entities.ReservationBookEntity;
import web.backend.gothere.Repositories.Entities.ScheduleEntity;
import web.backend.gothere.Repositories.Entities.ScheduleTableReservationEntity;
import web.backend.gothere.Repositories.Entities.TableEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Entities.UserRole;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.TableRepository;
import web.backend.gothere.Repositories.Interfaces.ConfirmationTokenRepository;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;
import web.backend.gothere.Repositories.Interfaces.ReservationBookRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleRepository;
import web.backend.gothere.Repositories.Interfaces.ScheduleTableReservationRepository;
import web.backend.gothere.Repositories.Interfaces.UserOfferRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;

@Configuration
public class InitDB {
    @Bean
    CommandLineRunner initDataBase(OffersRepository offerR, 
        UserOfferRepository userofferR, 
        UserRepository userR, 
        BarRepository barR, 
        TableRepository barTableR,
        ReservationBookRepository reservationBookR,
        ScheduleTableReservationRepository scheduleTableReservationR,
        ScheduleRepository scheduleR,
        ConfirmationTokenRepository confirmationTokenR
        ){
            
        return args -> {
            //hoarios
            scheduleR.save(new ScheduleEntity(LocalTime.of(23,00,00),LocalTime.of(02,00,00), null));
            scheduleR.save(new ScheduleEntity(LocalTime.of(21,00,00),LocalTime.of(00,00,00), null));
            scheduleR.save(new ScheduleEntity(LocalTime.of(14,00,00),LocalTime.of(16,00,00), null));
            scheduleR.save(new ScheduleEntity(LocalTime.of(16,00,00),LocalTime.of(20,00,00), null));
            scheduleR.save(new ScheduleEntity(LocalTime.of(20,00,00),LocalTime.of(22,00,00), null));
            scheduleR.save(new ScheduleEntity(LocalTime.of(23,00,00),LocalTime.of(02,00,00), null));
            
            //List<ScheduleEntity> horarios = Arrays.asList(scheduleR.findById(1L).get(),scheduleR.findById(2L).get(),scheduleR.findById(4L).get());
         
            //bares
            barR.save(new BarEntity("name", "description", "66666666", "direccion", 41.649651, -0.887482, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 1", "description", "66666666", "direccion", 41.65463054785235, -0.8769452428088323, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 2", "description", "66666666", "direccion", 41.65546519593979, -0.8874342960022673, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 3", "description", "66666666", "direccion", 41.65728155577524, -0.8925120896989844, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 4", "description", "66666666", "direccion", 41.65323258381404, -0.8852162981728614, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 5", "description", "66666666", "direccion", 41.65732467279446, -0.885855207273458, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("name 6", "description", "66666666", "direccion", 41.65632401991278, -0.8771833249397168, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("bar de touluse", "description", "66666666", "direccion", 43.80017130521276, 1.5384482791887533, "schedule", 25, 90, 80, null));
            barR.save(new BarEntity("bar de madrid", "description", "66666666", "direccion", 40.41961249648086, -3.696045934742639, "schedule", 25, 90, 80, null));
        
               
             //mesas
            barTableR.save(new TableEntity(4,false,barR.findById(1L).get(),null));
            barTableR.save(new TableEntity(5,false,barR.findById(1L).get(),null));
            barTableR.save(new TableEntity(8,false,barR.findById(1L).get(),null));
            barTableR.save(new TableEntity(3,false,barR.findById(2L).get(),null));
            barTableR.save(new TableEntity(2,false,barR.findById(2L).get(),null));
            barTableR.save(new TableEntity(1,false,barR.findById(2L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(2L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(2L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(3L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(4L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(5L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(6L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(7L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(8L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(7L).get(),null));

            //horarios mesas
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(1L).get(), scheduleR.findById(1L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(2L).get(), scheduleR.findById(2L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(3L).get(), scheduleR.findById(3L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(3L).get(), scheduleR.findById(4L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(3L).get(), scheduleR.findById(3L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(4L).get(), scheduleR.findById(1L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(4L).get(), scheduleR.findById(2L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(1L).get(), scheduleR.findById(4L).get()));

           //ofertas
           offerR.save(new OfferEntity("oferta de prueba", "dos jarricas", "hola.jpg", 2.3, 23, 23,  LocalDate.now(), LocalDate.now(), barR.findById(1L).get() ));
           offerR.save(new OfferEntity("oferta de prueba 2", "holas", "hola.jpg", 2.3, 23, 23, LocalDate.now(), LocalDate.now(),  barR.findById(2L).get() ));
           offerR.save(new OfferEntity("titulo oferta ", "desciption", "img.png", 22.50, 15, 0, LocalDate.of(2021,05,30), LocalDate.of(2022, 01, 01), barR.findById(1L).get() ));

           //usuarios
           BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            final String encryptedPassword = bCryptPasswordEncoder.encode("1234");
           
           UserEntity user1 = userR.save(new UserEntity("holsda@gmail.com", "ruben", "valero", encryptedPassword,"12345667"));
           confirmationTokenR.save(new ConfirmationTokenEntity(user1));
           UserEntity user2 =  userR.save(new UserEntity("hola@gmail.com", "ruben", "valero", encryptedPassword,"234123412"));
            confirmationTokenR.save(new ConfirmationTokenEntity(user2));
            
            // //reservas
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.now(), false, scheduleTableReservationR.findById(1L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), false, scheduleTableReservationR.findById(3L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), true, scheduleTableReservationR.findById(4L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), true, scheduleTableReservationR.findById(5L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.of(2021,05,1), false, scheduleTableReservationR.findById(6L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2023,8,21), true, scheduleTableReservationR.findById(7L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2023,8,24), false, scheduleTableReservationR.findById(5L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2021,05,23), false, scheduleTableReservationR.findById(5L).get()));
           
            //user-token
            // BarEntity barTest = barR.findById(2L).get();
            // UserEntity userTest = userR.findById(3L).get();
            UserEntity userTest = new UserEntity("aa", "aa", "aa", encryptedPassword , "1234");
            userTest.setUserRole(UserRole.BAR);
            BarEntity barTest = new BarEntity();
            barTest.setName("La viña de Martín");
            barTest.setAllowedCapacity(100);
            barTest.setCurrentCapacity(2);
            barTest.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget ultricies est. Nam porta mi ");
            barTest.setAddress("calle Lolo n 2,1 Zaragoza");
            barTest.setLatitude(-0.8725120896989844);
            barTest.setLength(41.65828155577524);
            barTest.setUser(userTest);
            userTest.setBar(barTest);
            userR.save(userTest);
            confirmationTokenR.save(new ConfirmationTokenEntity(userTest));

            //mesas
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(5,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(8,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(3,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(2,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(1,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));
            barTableR.save(new TableEntity(4,false,barR.findById(10L).get(),null));

            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(16L).get(), scheduleR.findById(1L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(17L).get(), scheduleR.findById(2L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(17L).get(), scheduleR.findById(3L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(19L).get(), scheduleR.findById(4L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(20L).get(), scheduleR.findById(3L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(21L).get(), scheduleR.findById(1L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(20L).get(), scheduleR.findById(2L).get()));
            scheduleTableReservationR.save(new ScheduleTableReservationEntity(barTableR.findById(29L).get(), scheduleR.findById(4L).get()));

            offerR.save(new OfferEntity("oferta de prueba", "dos jarricas", "hola.jpg", 2.3, 23, 23,  LocalDate.now(), LocalDate.now(), barR.findById(10L).get() ));
            offerR.save(new OfferEntity("oferta de prueba 2", "holas", "hola.jpg", 2.3, 23, 23, LocalDate.now(), LocalDate.now(),  barR.findById(10L).get() ));
            offerR.save(new OfferEntity("titulo oferta ", "desciption", "img.png", 22.50, 15, 0, LocalDate.of(2021,05,30), LocalDate.of(2022, 01, 01), barR.findById(10L).get() ));

            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.now(), false, scheduleTableReservationR.findById(9L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), false, scheduleTableReservationR.findById(10L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), true, scheduleTableReservationR.findById(11L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.now(), true, scheduleTableReservationR.findById(12L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(2L).get(),LocalDate.of(2021,05,1), false, scheduleTableReservationR.findById(13L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2023,8,21), true, scheduleTableReservationR.findById(14L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2023,8,24), false, scheduleTableReservationR.findById(15L).get()));
            reservationBookR.save(new ReservationBookEntity( userR.findById(1L).get(),LocalDate.of(2021,05,23), false, scheduleTableReservationR.findById(16L).get()));
           
            
        };
    }
}
