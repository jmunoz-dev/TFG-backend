package web.backend.gothere.Repositories.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "BarImgs")
@Entity(name = "BarImgs")

public class BarImgsEntity {
    
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)Long idImgBar;
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name="idBar")
    private BarEntity bar;


    public BarImgsEntity() {}

    public BarImgsEntity(String imgUrl, BarEntity bar, Long idImgBar ) {
        this.imgUrl = imgUrl;
        this.bar = bar;
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

    public BarEntity getBar() {
        return bar;
    }

    public void setBar(BarEntity bar) {
        this.bar = bar;
    }

 

  
  

}
