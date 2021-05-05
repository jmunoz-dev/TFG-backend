package web.backend.gothere.Services.Models;

import java.time.LocalDate;

public class ReservationBookDTO {

    private Long idReservationBook;
    private UserDTO user;
    private BarTableDTO barTable;
    private LocalDate checkInHour;
    private LocalDate checkOutHour;


    public ReservationBookDTO() {
    }

    public ReservationBookDTO(UserDTO user, BarTableDTO barTable, LocalDate checkInHour, LocalDate checkOutHour) {
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