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
    TODO:       (No se si también tendría que tener Reserva, lo dejo comentado) nop

    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScheduleTableReservation;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTable;
    @ManyToOne
    @JoinColumn(name="idSchedule")
    private ScheduleEntity schedule;

    
    public ScheduleTableReservationEntity() {
    }
    
    public ScheduleTableReservationEntity(Long idScheduleTableReservation, BarTableEntity barTable,
            ScheduleEntity schedule) {
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
    public BarTableEntity getBarTableEntity() {
        return barTable;
    }
    public void setBarTableEntity(BarTableEntity barTable) {
        this.barTable = barTable;
    }
    public ScheduleEntity getScheduleEntity() {
        return schedule;
    }
    public void setScheduleEntity(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    

}
