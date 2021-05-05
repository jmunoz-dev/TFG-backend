package web.backend.gothere.Repositories.Entities;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ReservationBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTable;

    private LocalDate checkInHour;
    private LocalDate checkOutHour;

    public ReservationBookEntity() {
    }

    public ReservationBookEntity(UserEntity user, BarTableEntity barTable, LocalDate checkInHour, LocalDate checkOutHour) {
        this.user = user;
        this.barTable = barTable;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
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

    public LocalDate getCheckInHour() {
        return this.checkInHour;
    }

    public void setCheckInHour(LocalDate checkInHour) {
        this.checkInHour = checkInHour;
    }

    public LocalDate getCheckOutHour() {
        return this.checkOutHour;
    }

    public void setCheckOutHour(LocalDate checkOutHour) {
        this.checkOutHour = checkOutHour;
    }
    
}