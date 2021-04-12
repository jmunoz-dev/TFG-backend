package web.backend.Repositories.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Offers")
@Entity(name = "Offers")

public class OfferEntity {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long idOffer;
    private String offerTitle;
    private String offerDescription;
    private String offerImage;
    private double offerPrice;
    private int offerRewardsPoints;
    private int offerMinimunPoints;
    private long offerQrCode;
    private LocalDate startDate;
    private LocalDate endDate;
    // @ManyToOne
    // @JoinColumn(name="idBar")
    // private BarEntity bar;


    public OfferEntity() {
    }

    public OfferEntity(String offerTitle, String offerDescription, String offerImage, double offerPrice, int offerRewardsPoints, int offerMinimunPoints, long offerQrCode, LocalDate startDate, LocalDate endDate) {
        this.offerTitle = offerTitle;
        this.offerDescription = offerDescription;
        this.offerImage = offerImage;
        this.offerPrice = offerPrice;
        this.offerRewardsPoints = offerRewardsPoints;
        this.offerMinimunPoints = offerMinimunPoints;
        this.offerQrCode = offerQrCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    // public OfferEntity(String offerTitle, String offerDescription, String offerImage, double offerPrice, int offerRewardsPoints, long offerQrCode, LocalDate startDate, LocalDate endDate, BarsEntity bar) {
    //     this.offerTitle = offerTitle;
    //     this.offerDescription = offerDescription;
    //     this.offerImage = offerImage;
    //     this.offerPrice = offerPrice;
    //     this.offerRewardsPoints = offerRewardsPoints;
    //     this.offerQrCode = offerQrCode;
    //     this.startDate = startDate;
    //     this.endDate = endDate;
    //     this.bar = bar;
    // }
    

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

    public long getOfferQrCode() {
        return this.offerQrCode;
    }

    public void setOfferQrCode(long offerQrCode) {
        this.offerQrCode = offerQrCode;
    }

    // public BarsEntity getBar() {
    //     return this.bar;
    // }

    // public void setBar(BarsEntity bar) {
    //     this.bar = bar;
    // }


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

    public int getOfferMinimunPoints() {
        return this.offerMinimunPoints;
    }

    public void setOfferMinimunPoints(int offerMinimunPoints) {
        this.offerMinimunPoints = offerMinimunPoints;
    }


}


