package web.backend.gothere.Repositories.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="ScheduleTableReservation")
@Entity(name="ScheduleTableReservation")
public class ScheduleTableReservationEntity {
    

    /*  
    
    ESTA ES LA TABLA HORARIO-MESA
        tiene:  id 
                Horario
                Mesa
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScheduleTableReservation;
    @ManyToOne
    @JoinColumn(name = "idTable")
    private TableEntity table;
    @ManyToOne
    @JoinColumn(name="idSchedule")
    private ScheduleEntity schedule;

    
    public ScheduleTableReservationEntity() {
    }
    
    public ScheduleTableReservationEntity( TableEntity table,
            ScheduleEntity schedule) {
        this.table = table;
        this.schedule = schedule;
    }

    public Long getIdScheduleTableReservation() {
        return idScheduleTableReservation;
    }
    public void setIdScheduleTableReservation(Long idScheduleTableReservation) {
        this.idScheduleTableReservation = idScheduleTableReservation;
    }
    public TableEntity getTable() {
        return table;
    }
    public void setTable(TableEntity table) {
        this.table = table;
    }
    public ScheduleEntity getSchedule() {
        return schedule;
    }
    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    

}
