package web.backend.gothere.Services.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationBookDTO {

    private Long idReservationBook;
    private UserDTO user;
    private BarTableDTO barTable;
    private LocalDateTime checkInHour;
    private LocalDateTime checkOutHour;
    private boolean canceled;


    public ReservationBookDTO() {
    }

    public ReservationBookDTO(UserDTO user, BarTableDTO barTable, LocalDateTime checkInHour, LocalDateTime checkOutHour) {
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

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BarTableDTO getBarTable() {
        return this.barTable;
    }

    public void setBarTable(BarTableDTO barTable) {
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