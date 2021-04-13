package web.backend.gothere.Services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.UserAlreadyExistException;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Entities.UserOfferEntity;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;
import web.backend.gothere.Repositories.Interfaces.UserOfferRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;
import web.backend.gothere.Services.Models.OfferDTO;
import web.backend.gothere.Services.Models.UserDTO;
import web.backend.gothere.Services.Models.UserOfferDTO;

public class UserOffersService {
    @Autowired
    private UserOfferRepository UserOfferRepository;
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private OffersRepository OfferRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserOfferDTO add(UserOfferDTO userOffer){
       Optional<UserOfferEntity> userOfferTemp= UserOfferRepository.findByUserAndOffer(
          UserRepository.findByEmail(userOffer.getUser().getEmail()).get(), OfferRepository.findById(userOffer.getOffer().getIdOffer()).get());

        if(userOfferTemp.isPresent()){
            throw new UserAlreadyExistException();
        }
        boolean isUnique = false;
        do{
            String code = generateCode();
            Optional<UserOfferEntity> exist = UserOfferRepository.findByCode(code);
            if(!exist.isPresent()){
                 isUnique = true;
                 userOffer.setCode(code);
            }
        }while(!isUnique);
        Optional<UserEntity> user = UserRepository.findByEmail(userOffer.getUser().getEmail());
        Optional<OfferEntity> offer = OfferRepository.findById(userOffer.getOffer().getIdOffer());
        UserOfferEntity entityToInsert = modelMapper.map(userOffer, UserOfferEntity.class);
        if(user.isPresent() && offer.isPresent()){
            entityToInsert.setOffer(offer.get());
            entityToInsert.setUser(user.get());
        }else{
            throw new UserAlreadyExistException();
        }
        
        UserOfferEntity result = UserOfferRepository.save(entityToInsert);
       return modelMapper.map(result, UserOfferDTO.class);
    }
    public List<UserOfferDTO> getAll() throws ResponseStatusException {
        
        List<UserOfferDTO> userOffersList = UserOfferRepository.findAll().stream()
        .map(x -> modelMapper.map(x, UserOfferDTO.class))
        .collect(Collectors.toList());

        if(!userOffersList.isEmpty()){
            return userOffersList;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }
    private String generateCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    
        return generatedString.toUpperCase();
    }
}
