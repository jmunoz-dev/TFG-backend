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
    TODO:       (No se si también tendría que tener Reserva, lo dejo comentado)

    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScheduleTableReservation;
    // @ManyToOne
    // @JoinColumn(name = "idReservationBook")
    // private ReservationBookEntity reservationBookEntity;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTableEntity;
    @ManyToOne
    @JoinColumn(name="idSchedule")
    private ScheduleEntity scheduleEntity;

}
