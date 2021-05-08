package web.backend.gothere.Services.Models;

import java.time.LocalTime;

public class ScheduleDTO {
    private Long idSchedule;
    private LocalTime checkInHour;
    private LocalTime checkOutHour;
    public ScheduleDTO() {
    }
    public ScheduleDTO(Long idSchedule, LocalTime checkInHour, LocalTime checkOutHour) {
        this.idSchedule = idSchedule;
        this.checkInHour = checkInHour;
        this.checkOutHour = checkOutHour;
    }
    public Long getIdSchedule() {
        return idSchedule;
    }
    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }
    public LocalTime getCheckInHour() {
        return checkInHour;
    }
    public void setCheckInHour(LocalTime checkInHour) {
        this.checkInHour = checkInHour;
    }
    public LocalTime getCheckOutHour() {
        return checkOutHour;
    }
    public void setCheckOutHour(LocalTime checkOutHour) {
        this.checkOutHour = checkOutHour;
    }

    
}
