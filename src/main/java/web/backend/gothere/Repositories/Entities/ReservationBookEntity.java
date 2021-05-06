package web.backend.gothere.Repositories.Entities;

import java.time.LocalDateTime;
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

    private LocalDateTime checkInHour;
    private LocalDateTime checkOutHour;
    private boolean canceled;

    public ReservationBookEntity() {
    }

    public ReservationBookEntity(UserEntity user, BarTableEntity barTable, LocalDateTime checkInHour, LocalDateTime checkOutHour, boolean canceled) {
        this.user = user;
        this.barTable = barTable;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
        this.canceled = false;
    }


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
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

    public LocalDateTime getCheckInHour() {
        return this.checkInHour;
    }

    public void setCheckInHour(LocalDateTime checkInHour) {
        this.checkInHour = checkInHour;
    }

    public LocalDateTime getCheckOutHour() {
        return this.checkOutHour;
    }

    public void setCheckOutHour(LocalDateTime checkOutHour) {
        this.checkOutHour = checkOutHour;
    }
    
}