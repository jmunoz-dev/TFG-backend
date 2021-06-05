package web.backend.gothere.Repositories.Entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="ReservationBooks")
@Entity(name="ReservationBooks")
public class ReservationBookEntity {


    /*  
    ESTA ES LA TABLA RESERVAS
        tiene:  id 
                Usuario (tabla user)
                Fecha (LocalDateTime)
                Cancelado (booleano)
                Horario-Mesa (tabla de ScheduleTableReservation)
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    private LocalDate reservationDate;
    private boolean canceled;

    @ManyToOne
    @JoinColumn(name = "idScheduleTableReservation")
    private ScheduleTableReservationEntity scheduleTableReservation;

    public ReservationBookEntity() {
    }

    public ScheduleTableReservationEntity getScheduleTableReservation() {
        return scheduleTableReservation;
    }

    public void setScheduleTableReservation(ScheduleTableReservationEntity scheduleTableReservation) {
        this.scheduleTableReservation = scheduleTableReservation;
    }

   

    public ReservationBookEntity( UserEntity user, LocalDate reservationDate, boolean canceled,
            ScheduleTableReservationEntity scheduleTableReservation) {
        this.user = user;
        this.reservationDate = reservationDate;
        this.canceled = canceled;
        this.scheduleTableReservation = scheduleTableReservation;
    }

    public Long getIdReservationBook() {
        return this.idReservationBook;
    }

    public void setIdReservationBook(Long idReservationBook) {
        this.idReservationBook = idReservationBook;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDate getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}