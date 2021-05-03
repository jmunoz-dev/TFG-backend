package web.backend.gothere.Repositories.Entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "BarImgs")
@Entity(name = "BarImgs")

public class BarImgsEntity {
    
    private String imgUrl;
    private BarEntity barEntity;


    public BarImgsEntity() {}

    public BarImgsEntity(String imgUrl, BarEntity barEntity ) {
        this.imgUrl = imgUrl;
        this.barEntity = barEntity;
    }

}
