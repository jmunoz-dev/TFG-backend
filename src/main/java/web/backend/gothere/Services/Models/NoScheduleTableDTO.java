package web.backend.gothere.Services.Models;

public class NoScheduleTableDTO {
    private Long idTable;
    private int capacity;
    private boolean reservated;
    private BarDTO bar;

    public NoScheduleTableDTO() {
    }

  

    public NoScheduleTableDTO( int capacity, boolean reservated, BarDTO bar) {
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
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

    public BarDTO getBar() {
        return bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public void setReservated(boolean reservated) {
        this.reservated = reservated;
    }
}
