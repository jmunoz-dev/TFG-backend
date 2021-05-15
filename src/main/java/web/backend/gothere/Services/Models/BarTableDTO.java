package web.backend.gothere.Services.Models;

import java.util.List;

public class BarTableDTO {

    private Long idBarTable;
    private int capacity;
    private boolean reservated;
    private BarDTO bar;
    private List<ScheduleTableReservationDTO> scheduleTableReservations;

    public BarTableDTO() {
    }

  

    public BarTableDTO( int capacity, boolean reservated, BarDTO bar, List<ScheduleTableReservationDTO> scheduleTableReservations) {
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
        this.scheduleTableReservations = scheduleTableReservations;
    }

    public List<ScheduleTableReservationDTO> getScheduleTableReservations() {
        return scheduleTableReservations;
    }

    public void setScheduleTableReservations(List<ScheduleTableReservationDTO> scheduleTableReservations) {
        this.scheduleTableReservations = scheduleTableReservations;
    }

    public Long getIdBarTable() {
        return this.idBarTable;
    }

    public void setIdBarTable(Long idBarTable) {
        this.idBarTable = idBarTable;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isReservated() {
        return this.reservated;
    }

    public boolean getReservated() {
        return this.reservated;
    }

    public void setReservated(boolean reservated) {
        this.reservated = reservated;
    }

   

}
