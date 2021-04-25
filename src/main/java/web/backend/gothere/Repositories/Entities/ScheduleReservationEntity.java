package web.backend.gothere.Repositories.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ScheduleReservation")
@Entity(name = "ScheduleReservation")

public class ScheduleReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScheduleReservation;
    private LocalDate startHour;
    private LocalDate endHour;

    public ScheduleReservationEntity() {
    }

    public ScheduleReservationEntity(LocalDate startHour, LocalDate endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Long getIdScheduleReservation() {
        return this.idScheduleReservation;
    }

    public void setIdScheduleReservation(Long idScheduleReservation) {
        this.idScheduleReservation = idScheduleReservation;
    }

    public LocalDate getStartHour() {
        return this.startHour;
    }

    public void setStartHour(LocalDate startHour) {
        this.startHour = startHour;
    }

    public LocalDate getEndHour() {
        return this.endHour;
    }

    public void setEndHour(LocalDate endHour) {
        this.endHour = endHour;
    }

}
