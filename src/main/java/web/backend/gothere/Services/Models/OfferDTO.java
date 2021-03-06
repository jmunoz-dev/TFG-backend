package web.backend.gothere.Services.Models;

import java.time.LocalDate;

public class OfferDTO {
    private Long idOffer;
    private String offerTitle;
    private String offerDescription;
    private String offerImage;
    private double offerPrice;
    private int offerRewardsPoints;
    private int offerMinimunPoints;
    private LocalDate startDate;
    private LocalDate endDate;
    private BarDTO bar;

    public OfferDTO(){}

    public OfferDTO(String offerTitle, String offerDescription, String offerImage, double offerPrice, int offerRewardsPoints, long offerQrCode, LocalDate startDate, LocalDate endDate, BarDTO bar) {
        this.offerTitle = offerTitle;
        this.offerDescription = offerDescription;
        this.offerImage = offerImage;
        this.offerPrice = offerPrice;
        this.offerRewardsPoints = offerRewardsPoints;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bar = bar;
    }

    public long getIdOffer() {
        return this.idOffer;
    }

    public void setIdOffer(long idOffer) {
        this.idOffer = idOffer;
    }

    public String getOfferTitle() {
        return this.offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferDescription() {
        return this.offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getOfferImage() {
        return this.offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }

    public double getOfferPrice() {
        return this.offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getOfferRewardsPoints() {
        return this.offerRewardsPoints;
    }

    public void setOfferRewardsPoints(int offerRewardsPoints) {
        this.offerRewardsPoints = offerRewardsPoints;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BarDTO getBar() {
        return this.bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public int getOfferMinimunPoints() {
        return this.offerMinimunPoints;
    }

    public void setOfferMinimunPoints(int offerMinimunPoints) {
        this.offerMinimunPoints = offerMinimunPoints;
    }
 
}
