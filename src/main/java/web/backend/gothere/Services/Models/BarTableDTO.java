package web.backend.gothere.Services.Models;

public class BarTableDTO {

    private Long idBarTable;
    private int capacity;
    private boolean reservated;
    private BarDTO bar;

    public BarTableDTO() {
    }

    public BarTableDTO(int capacity, boolean reservated, BarDTO bar) {
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
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
