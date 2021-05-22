package web.backend.gothere.Services.Models;

import java.util.List;

public class BarDTO {

    private Long idbar;
    private String name;
    private String description;
    private String phone;
    private String address;
    private double latitude;
    private double length;
    private String schedule;
    private int totalCapacity;
    private int allowedCapacity;
    private int currentCapacity;
    
    private List<BarImgsDTO> barImages;
    

    public BarDTO(Long idbar, String name, String description, String phone, String address, double latitude,
    double length, String schedule, int totalCapacity, int allowedCapacity, int currentCapacity) {
        this.idbar = idbar;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.latitude = latitude;
        this.length = length;
        this.schedule = schedule;
        this.totalCapacity = totalCapacity;
        this.allowedCapacity = allowedCapacity;
        this.currentCapacity = currentCapacity;
    }

    public BarDTO() {
    }

    public Long getIdbar() {
        return this.idbar;
    }

    public void setIdbar(Long idbar) {
        this.idbar = idbar;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<BarImgsDTO> getBarImages() {
        return barImages;
    }

    public void setBarImages(List<BarImgsDTO> barImages) {
        this.barImages = barImages;
    }

   
}
