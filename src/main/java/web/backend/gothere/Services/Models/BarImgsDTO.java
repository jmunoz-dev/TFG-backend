package web.backend.gothere.Services.Models;

import web.backend.gothere.Repositories.Entities.BarEntity;

public class BarImgsDTO {
    
      
    private Long idImgBar;
    private String imgUrl;
    private BarEntity barEntity;


    public BarImgsDTO() {}

    public BarImgsDTO(String imgUrl, BarEntity barEntity, Long idImgBar ) {
        this.imgUrl = imgUrl;
        this.barEntity = barEntity;
        this.idImgBar = idImgBar;
    }

    public Long getIdImgBar() {
        return idImgBar;
    }

    public void setIdImgBar(Long idImgBar) {
        this.idImgBar = idImgBar;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

 

    public void setBarEntity(BarEntity barEntity) {
        this.barEntity = barEntity;
    }
}
