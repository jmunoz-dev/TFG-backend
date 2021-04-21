package web.backend.gothere.Services.Models;

public class SimpleUserOfferDTO {


    public OfferDTO offer;
    
    private boolean used;

    public SimpleUserOfferDTO() {
    }

    public SimpleUserOfferDTO(OfferDTO offer, boolean used) {
        this.offer = offer;
        this.used = used;
    }
  
    public OfferDTO getOffer() {
        return offer;
    }

    public void setOffer(OfferDTO offer) {
        this.offer = offer;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

  
}


