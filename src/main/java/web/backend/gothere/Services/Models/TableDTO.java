package web.backend.gothere.Services.Models;

import java.util.List;

public class TableDTO {

    private Long idTable;
    private int capacity;
    private boolean reservated;
    private BarDTO bar;
    private String num;
    private List<ScheduleTableReservationDTO> scheduleTableReservations;

    public TableDTO() {
    }

    public TableDTO( int capacity, boolean reservated, BarDTO bar, List<ScheduleTableReservationDTO> scheduleTableReservations, String num) {
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
        this.scheduleTableReservations = scheduleTableReservations;
        this.num = num;
    }

    public List<ScheduleTableReservationDTO> getScheduleTableReservations() {
        return scheduleTableReservations;
    }

    public void setScheduleTableReservations(List<ScheduleTableReservationDTO> scheduleTableReservations) {
        this.scheduleTableReservations = scheduleTableReservations;
    }

    public Long getIdTable() {
        return this.idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
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
        return bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

   

}
