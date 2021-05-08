package web.backend.gothere.Services.Models;

public class ScheduleTableReservationDTO {
    private Long idScheduleTableReservation;
    private BarTableDTO barTable;
    private ScheduleDTO schedule;
    public ScheduleTableReservationDTO() {
    }
    public ScheduleTableReservationDTO(Long idScheduleTableReservation, BarTableDTO barTable, ScheduleDTO schedule) {
        this.idScheduleTableReservation = idScheduleTableReservation;
        this.barTable = barTable;
        this.schedule = schedule;
    }
    public Long getIdScheduleTableReservation() {
        return idScheduleTableReservation;
    }
    public void setIdScheduleTableReservation(Long idScheduleTableReservation) {
        this.idScheduleTableReservation = idScheduleTableReservation;
    }
    public BarTableDTO getBarTable() {
        return barTable;
    }
    public void setBarTable(BarTableDTO barTable) {
        this.barTable = barTable;
    }
    public ScheduleDTO getSchedule() {
        return schedule;
    }
    public void setSchedule(ScheduleDTO schedule) {
        this.schedule = schedule;
    }
   
   
}
