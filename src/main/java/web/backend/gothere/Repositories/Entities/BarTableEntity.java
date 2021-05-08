package web.backend.gothere.Repositories.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "BarTables")
@Entity(name = "BarTables")
public class BarTableEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long idBarTable;
    private int capacity;
    private boolean reservated;
    @ManyToOne
    @JoinColumn(name="idBar")
    private BarEntity bar;
    
    // TODO - Asociar con la tabla de reservas central
    
    @OneToMany
    @JoinColumn(name="idTablesSchedule")
    private List<ScheduleEntity> schedules;


    public BarTableEntity() {
    }

  

    public BarTableEntity(Long idBarTable, int capacity, boolean reservated, BarEntity bar,
            List<ScheduleEntity> schedules) {
        this.idBarTable = idBarTable;
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
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


   

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleEntity> schedules) {
        this.schedules = schedules;
    }

    public BarEntity getBar() {
        return this.bar;
    }

    public void setBar(BarEntity bar) {
        this.bar = bar;
    }
    
}
