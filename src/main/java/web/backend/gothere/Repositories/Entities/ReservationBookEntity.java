package web.backend.gothere.Repositories.Entities;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ReservationBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservationBook;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "idBarTable")
    private BarTableEntity barTable;

    private LocalDate entrada;
    private LocalDate salida;

    public ReservationBookEntity() {
    }

    public ReservationBookEntity(UserEntity user, BarTableEntity barTable, LocalDate entrada, LocalDate salida) {
        this.user = user;
        this.barTable = barTable;
        this.entrada = entrada;
        this.salida = salida;
    }


    public Long getIdReservationBook() {
        return this.idReservationBook;
    }

    public void setIdReservationBook(Long idReservationBook) {
        this.idReservationBook = idReservationBook;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BarTableEntity getBarTable() {
        return this.barTable;
    }

    public void setBarTable(BarTableEntity barTable) {
        this.barTable = barTable;
    }

    public LocalDate getEntrada() {
        return this.entrada;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public LocalDate getSalida() {
        return this.salida;
    }

    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }


}