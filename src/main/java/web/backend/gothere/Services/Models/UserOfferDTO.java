package web.backend.gothere.Services.Models;

public class UserOfferDTO {
    public Long id;

    public UserDTO user;

    public OfferDTO offer;

    public String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public OfferDTO getOffer() {
        return offer;
    }

    public void setOffer(OfferDTO offer) {
        this.offer = offer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserOfferDTO() {
    }

    public UserOfferDTO(Long id, UserDTO user, OfferDTO offer, String code) {
        this.id = id;
        this.user = user;
        this.offer = offer;
        this.code = code;
    }

  
}
