package web.backend.gothere.Repositories.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "Bars")
@Entity(name = "Bars")

// BARES: idbar, qrcode, latitud, longitud, nombre, descripción,
// dirección, tlfContacto, horario (L:9:30 a2:30 x ejemplo),
// AforoActual , AforoTotal, AforoPermitido(porcentajes)

public class BarEntity {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idBar;
    private String name;
    private String description;
    private String phone;
    private String direction;
    private double latitude;
    private double length;
    private String schedule;
    private int totalCapacity;
    private int allowedCapacity;
    private int currentCapacity;

    @OneToOne(mappedBy = "bar")
    private UserEntity user;

    public BarEntity() {}

    public BarEntity(String name, String description, String phone, String direction, double latitude, double length, String schedule, int totalCapacity, int allowedCapacity, int currentCapacity, UserEntity user) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.direction = direction;
        this.latitude = latitude;
        this.length = length;
        this.schedule = schedule;
        this.totalCapacity = totalCapacity;
        this.allowedCapacity = allowedCapacity;
        this.currentCapacity = currentCapacity;
        this.user =  user;
    }

    public Long getIdBar() {
        return this.idBar;
    }

    public void setIdBar(Long idBar) {
        this.idBar = idBar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getTotalCapacity() {
        return this.totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getAllowedCapacity() {
        return this.allowedCapacity;
    }

    public void setAllowedCapacity(int allowedCapacity) {
        this.allowedCapacity = allowedCapacity;
    }

    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
   
}