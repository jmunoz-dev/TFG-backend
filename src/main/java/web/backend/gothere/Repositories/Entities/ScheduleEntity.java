package web.backend.gothere.Repositories.Entities;

import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Table(name="Schedules")
@Entity(name="Schedules")
public class ScheduleEntity {
    
    /*  
    
    ESTA ES LA TABLA HORARIOS
        tiene:  id 
                horario de entrada
                horario de salida
    */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    private LocalTime checkInHour;
    private LocalDateTime checkOutHour;


    public ScheduleEntity() {
    }

    public ScheduleEntity(LocalTime checkInHour, LocalDateTime checkOutHour) {
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
    }

    public Long getIdReservationBook() {
        return this.idReservationBook;
    }

    public void setIdReservationBook(Long idReservationBook) {
        this.idReservationBook = idReservationBook;
    }

    public LocalTime getCheckInHour() {
        return this.checkInHour;
    }

    public void setCheckInHour(LocalTime checkInHour) {
        this.checkInHour = checkInHour;
    }

    public LocalDateTime getCheckOutHour() {
        return this.checkOutHour;
    }

    public void setCheckOutHour(LocalDateTime checkOutHour) {
        this.checkOutHour = checkOutHour;
    }
    
}
