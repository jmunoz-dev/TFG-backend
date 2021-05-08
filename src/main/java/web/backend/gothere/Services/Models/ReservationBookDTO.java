package web.backend.gothere.Services.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationBookDTO {

    private Long idReservationBook;
    private UserDTO user;
    private LocalDate reservationDate;
    private ScheduleTableReservationDTO scheduleTableReservation;
    private boolean canceled;


    public ReservationBookDTO() {
    }
    

    public ReservationBookDTO( UserDTO user, LocalDate reservationDate,
             boolean canceled, ScheduleTableReservationDTO scheduleTableReservation) {
        this.user = user;
        this.reservationDate = reservationDate;
        this.scheduleTableReservation = scheduleTableReservation;
        this.canceled = canceled;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ScheduleTableReservationDTO getScheduleTableReservation() {
        return scheduleTableReservation;
    }

    public void setScheduleTableReservation(ScheduleTableReservationDTO scheduleTableReservation) {
        this.scheduleTableReservation = scheduleTableReservation;
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

}