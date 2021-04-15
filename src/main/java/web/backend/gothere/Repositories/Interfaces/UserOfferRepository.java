package web.backend.gothere.Repositories.Interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Entities.UserOfferEntity;

public interface UserOfferRepository extends JpaRepository<UserOfferEntity, Long>{
    Optional<UserOfferEntity> findByCode(String code);

    @Query(value = "SELECT uo FROM UserOffers uo WHERE uo.User = :idUser AND uo.offer = :idOffer")
    Optional<UserOfferEntity> findByUserAndOffer(@Param("idUser") UserEntity idUser,@Param("idOffer") OfferEntity idOffer);

    @Query(value = "SELECT uo FROM UserOffers uo "+
    "INNER JOIN Offers o "+
    "ON o.idOffer = ou.idOffer "+
     "WHERE uo.User = :user AND o.Bar = :b")
    List<UserOfferEntity> findByUserAndBar(@Param("user") UserEntity user,  @Param("bar") OfferEntity bar);

}
