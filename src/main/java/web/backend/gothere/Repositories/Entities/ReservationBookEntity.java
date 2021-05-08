package web.backend.gothere.Repositories.Entities;

import java.time.LocalDateTime;

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

    TODO:       comprobar que la referencia a scheduleTableReservationEntity está bien hecha
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTable;

    private LocalDateTime reservationDate;
    private boolean canceled;

    @ManyToOne
    @JoinColumn(name = "idScheduleTableReservation")
    private ScheduleTableReservationEntity scheduleTableReservationEntity;

    public ReservationBookEntity() {
    }

    public ReservationBookEntity(UserEntity user, BarTableEntity barTable, LocalDateTime reservationDate, boolean canceled) {
        this.user = user;
        this.barTable = barTable;
        this.reservationDate = reservationDate;
        this.canceled = canceled;
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

    public BarTableEntity getBarTable() {
        return this.barTable;
    }

    public void setBarTable(BarTableEntity barTable) {
        this.barTable = barTable;
    }

    public LocalDateTime getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
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