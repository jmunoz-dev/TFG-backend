package web.backend.gothere.Services.Models;

public class SimpleUserOfferDTO {


    public OfferDTO offer;
    
    private boolean used;
    private String code;
    public SimpleUserOfferDTO() {
    }

    public SimpleUserOfferDTO(OfferDTO offer, boolean used, String code) {
        this.offer = offer;
        this.used = used;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
  
}


