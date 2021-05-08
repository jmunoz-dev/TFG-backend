package web.backend.gothere.Services.Models;

import java.util.List;

public class BarTableDTO {

    private Long idBarTable;
    private int capacity;
    private boolean reservated;
    private BarDTO bar;
    private List<ScheduleDTO> schedules;

    public BarTableDTO() {
    }

  

    public BarTableDTO(Long idBarTable, int capacity, boolean reservated, BarDTO bar, List<ScheduleDTO> schedules) {
        this.idBarTable = idBarTable;
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
        this.schedules = schedules;
    }

    public List<ScheduleDTO> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDTO> schedules) {
        this.schedules = schedules;
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

    public BarDTO getBar() {
        return this.bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }
}
