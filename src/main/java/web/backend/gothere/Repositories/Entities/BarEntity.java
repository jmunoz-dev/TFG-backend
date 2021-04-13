package web.backend.gothere.Repositories.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Bars")
@Entity(name="Bars")

//BARES: idbar, qrcode,  latitud, longitud, nombre, descripción,  
//dirección, tlfContacto, horario (L:9:30 a2:30 x ejemplo), 
//AforoActual , AforoTotal, AforoPermitido(porcentajes)

public class BarEntity{
//uso este autoincrementable en vez de el de dentro de los constructores
    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Long idbar;

   

    public BarEntity(Long qrcode, Long latitude,Long length, String name,String description,String direction,String phone,String schedule,int currentCapacity ,int totalCapacity,int allowedCapacity){
        this.qrcode = qrcode;
        this.latitude = latitude;
        this.length = length;
        this.name = name;
        this.description = description;
        this.direction = direction;
        this.phone = phone;
        this.schedule = schedule;
        this.currentCapacity = currentCapacity;
        this.totalCapacity = totalCapacity;
        this.allowedCapacity = allowedCapacity;
        
        
        

    }
    //getters y setters 
    private Long qrcode;
    public Long getQrcode() { return qrcode; }
    public void setQrcode(Long qrcode) { this.qrcode = qrcode;}

    private Long latitude;
    public Long getLatitude() { return latitude; }
    public void setLatitude(Long latitude) { this.latitude = latitude;}

    private Long length;
    public Long getLength() { return length; }
    public void setLength(Long length) { this.length = length;}

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    private String direction;
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction;}

    private String phone;
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone;}
    
    private String schedule;
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule;}

    private int currentCapacity;
    public int getCurrentCapacity() { return currentCapacity; }
    public void setCurrentCapacity(int currentCapacity) { this.currentCapacity = currentCapacity;}

    private int totalCapacity;
    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int totalCapacity) { this.totalCapacity = totalCapacity;}

    private int allowedCapacity;
    public int getAllowedCapacity() { return allowedCapacity; }
    public void setAllowedCapacity(int allowedCapacity) { this.allowedCapacity = allowedCapacity;}
    
    public Long getIdbar() { return idbar; }
    public void setIdbar(Long idbar) { this.idbar = idbar;}

    //constructores vacios solo con el autoincrem en los 3 models
    public BarEntity(){ 
        
    }
}