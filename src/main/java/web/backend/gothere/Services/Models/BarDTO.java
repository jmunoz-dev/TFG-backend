package web.backend.gothere.Services.Models;

public class BarDTO {
    

    private Long idbar;
    private Long latitude;
    private Long length;
    private String name;
    private String description;
    private String direction;
    private String phone;
    private String schedule;
    private int currentCapacity;
    private int totalCapacity;
    private int allowedCapacity;

    public Long getIdbar() {
        return idbar;
    }

    public void setIdbar(Long idbar) {
        this.idbar = idbar;
    }

    //
    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    //
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    //
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    //
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    //
    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
    //
    public int getAllowedCapacity() {
        return allowedCapacity;
    }

    public void setAllowedCapacity(int allowedCapacity) {
        this.allowedCapacity = allowedCapacity;
    }
}
