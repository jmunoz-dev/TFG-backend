package web.backend.gothere.Repositories.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ReservationBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTable;
    @ManyToOne
    @JoinColumn(name = "idScheduleReservation")
    private ScheduleReservationEntity scheduleReservation;

    public ReservationBook() {
    }

    public ReservationBook(UserEntity user, BarTableEntity barTable, ScheduleReservationEntity scheduleReservation) {
        this.user = user;
        this.barTable = barTable;
        this.scheduleReservation = scheduleReservation;
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

    public ScheduleReservationEntity getScheduleReservation() {
        return this.scheduleReservation;
    }

    public void setScheduleReservation(ScheduleReservationEntity scheduleReservation) {
        this.scheduleReservation = scheduleReservation;
    }

}