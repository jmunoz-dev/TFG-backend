package web.backend.Services.Models;

import java.time.LocalDate;

public class OfferDTO {
    private long idOffer;
    private String offerTitle;
    private String offerDescription;
    private double offerPrice;
    private int offerRewardsPoints;
    private long offerQrCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private BarsDTO bar;

    public OfferDTO(){}

    public OfferDTO(String offerTitle, String offerDescription, double offerPrice, int offerRewardsPoints, long offerQrCode, LocalDate startDate, LocalDate endDate, BarsDTO bar) {
        this.offerTitle = offerTitle;
        this.offerDescription = offerDescription;
        this.offerPrice = offerPrice;
        this.offerRewardsPoints = offerRewardsPoints;
        this.offerQrCode = offerQrCode;
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

    public long getOfferQrCode() {
        return this.offerQrCode;
    }

    public void setOfferQrCode(long offerQrCode) {
        this.offerQrCode = offerQrCode;
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

    public BarsDTO getBar() {
        return this.bar;
    }

    public void setBar(BarsDTO bar) {
        this.bar = bar;
    }
    
}
