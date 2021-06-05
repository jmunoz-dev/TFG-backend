package web.backend.gothere.Repositories.Entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Tables")
@Entity(name = "Tables")
public class TableEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long idTable;
    private int capacity;
    private boolean reservated;
    private String num;
    @ManyToOne
    @JoinColumn(name="idBar")
    private BarEntity bar;
    
    
    @OneToMany(mappedBy="table")
    private Set<ScheduleTableReservationEntity> ScheduleTableReservations;


    public TableEntity() {
    }

    public TableEntity(int capacity, boolean reservated, BarEntity bar,
            Set<ScheduleTableReservationEntity> scheduleTableReservations, String num) {
        this.capacity = capacity;
        this.reservated = reservated;
        this.bar = bar;
        this.ScheduleTableReservations = scheduleTableReservations;
        this.num = num;
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

    public Set<ScheduleTableReservationEntity> getScheduleTableReservations() {
        return ScheduleTableReservations;
    }

    public void setScheduleTableReservations(Set<ScheduleTableReservationEntity> scheduleTableReservations) {
        ScheduleTableReservations = scheduleTableReservations;
    }

    public BarEntity getBar() {
        return this.bar;
    }

    public void setBar(BarEntity bar) {
        this.bar = bar;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    
    
}
