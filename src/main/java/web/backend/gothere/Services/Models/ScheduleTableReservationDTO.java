package web.backend.gothere.Services.Models;

public class ScheduleTableReservationDTO {
    private Long idScheduleTableReservation;
    private TableDTO table;
    private ScheduleDTO schedule;
    public ScheduleTableReservationDTO() {
    }
    public ScheduleTableReservationDTO(Long idScheduleTableReservation, TableDTO table, ScheduleDTO schedule) {
        this.idScheduleTableReservation = idScheduleTableReservation;
        this.table = table;
        this.schedule = schedule;
    }
    public Long getIdScheduleTableReservation() {
        return idScheduleTableReservation;
    }
    public void setIdScheduleTableReservation(Long idScheduleTableReservation) {
        this.idScheduleTableReservation = idScheduleTableReservation;
    }
    
    public void setTable(TableDTO table) {
        this.table = table;
    }
    public ScheduleDTO getSchedule() {
        return schedule;
    }
    public void setSchedule(ScheduleDTO schedule) {
        this.schedule = schedule;
    }
    public TableDTO getTable() {
        return table;
    }
   
   
}
