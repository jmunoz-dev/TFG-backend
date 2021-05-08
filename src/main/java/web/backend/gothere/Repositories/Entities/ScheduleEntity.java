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
    private Long idSchedule;
    private LocalTime checkInHour;
    private LocalTime checkOutHour;


    public ScheduleEntity() {
    }

    public ScheduleEntity(LocalTime checkInHour, LocalTime checkOutHour) {
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
    }

    public Long getIdReservationBook() {
        return this.idSchedule;
    }

    public void setIdReservationBook(Long idReservationBook) {
        this.idSchedule = idReservationBook;
    }

    public LocalTime getCheckInHour() {
        return this.checkInHour;
    }

    public void setCheckInHour(LocalTime checkInHour) {
        this.checkInHour = checkInHour;
    }

    public LocalTime getCheckOutHour() {
        return this.checkOutHour;
    }

    public void setCheckOutHour(LocalTime checkOutHour) {
        this.checkOutHour = checkOutHour;
    }
    
}
