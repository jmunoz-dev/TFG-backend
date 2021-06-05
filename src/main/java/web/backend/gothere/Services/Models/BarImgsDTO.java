package web.backend.gothere.Services.Models;

public class BarImgsDTO {
    
      
    private Long idImgBar;
    private String imgUrl;
    private BarDTO bar;


    public BarImgsDTO() {}

    public BarImgsDTO(String imgUrl, BarDTO bar ) {
        this.imgUrl = imgUrl;
        this.bar = bar;
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

    public BarDTO getBar() {
        return bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

}
