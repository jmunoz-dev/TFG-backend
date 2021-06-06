package web.backend.gothere.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Exceptions.OfferCodeException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Entities.UserEntity;
import web.backend.gothere.Repositories.Entities.UserOfferEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;
import web.backend.gothere.Repositories.Interfaces.UserOfferRepository;
import web.backend.gothere.Repositories.Interfaces.UserRepository;
import web.backend.gothere.Services.Models.OfferDTO;
import web.backend.gothere.Services.Models.SimpleUserOfferDTO;
import web.backend.gothere.Services.Models.UserOfferDTO;

public class UserOffersService {
    @Autowired
    private UserOfferRepository UserOfferRepository;
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private OffersRepository OfferRepository;
    @Autowired
    private BarRepository BarRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String add(UserOfferDTO userOffer) {
        Optional<UserOfferEntity> userOfferTemp = UserOfferRepository.findByUserAndOffer(
                UserRepository.findByEmail(userOffer.getUser().getEmail()).get(),
                OfferRepository.findById(userOffer.getOffer().getIdOffer()).get());

        if (userOfferTemp.isPresent()) {
            return userOfferTemp.get().getCode();
        }
        boolean isUnique = false;
        int count = 0;
        do {
            String code = generateCode();
            Optional<UserOfferEntity> exist = UserOfferRepository.findByCode(code.toUpperCase());
            if (!exist.isPresent()) {
                isUnique = true;
                userOffer.setCode(code);
            }
            count++;
            if (count > 1000) {
                throw new OfferCodeException();
            }
        } while (!isUnique);
        Optional<UserEntity> user = UserRepository.findByEmail(userOffer.getUser().getEmail());
        Optional<OfferEntity> offer = OfferRepository.findById(userOffer.getOffer().getIdOffer());
        UserOfferEntity entityToInsert = modelMapper.map(userOffer, UserOfferEntity.class);
        if (user.isPresent() && offer.isPresent()) {
            entityToInsert.setOffer(offer.get());
            entityToInsert.setUser(user.get());
        } else {
            throw new ElementNotFoundException();
        }

        UserOfferEntity result = UserOfferRepository.save(entityToInsert);
        return result.getCode();
    }

    public List<UserOfferDTO> getAll() throws ResponseStatusException {

        List<UserOfferDTO> userOffersList = UserOfferRepository.findAll().stream()
                .map(x -> modelMapper.map(x, UserOfferDTO.class)).collect(Collectors.toList());

        if (!userOffersList.isEmpty()) {
            return userOffersList;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    private String generateCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return generatedString.toUpperCase();
    }

    public void deleteUserOffer(String code) {
        Optional<UserOfferEntity> entityToDelete = UserOfferRepository.findByCode(code.toUpperCase());
        if (entityToDelete.isPresent()) {
            UserOfferRepository.delete(entityToDelete.get());
        }
    }

    public OfferDTO setOfferUsed(String code) {
        Optional<UserOfferEntity> entityToUpdate = UserOfferRepository.findByCode(code.toUpperCase());
       
        if (entityToUpdate.isPresent()) {
            UserOfferDTO temp = modelMapper.map(entityToUpdate.get(), UserOfferDTO.class);
            if (temp.isUsed() || LocalDate.now().isAfter(temp.getOffer().getEndDate())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
            temp.setUsed(true);
            UserOfferRepository.save(modelMapper.map(temp, UserOfferEntity.class));
            temp.getOffer().getBar().setBarImages(null);
            return temp.getOffer();
        }
        throw new ElementNotFoundException();

    }
    public List<SimpleUserOfferDTO> getByUserAndBar(Long idBar, Long idUser){
        Optional<UserEntity> user = UserRepository.findById(idUser);
        Optional<BarEntity> bar = BarRepository.findById(idBar);

        if(bar.isPresent() && user.isPresent()){
           List<UserOfferDTO> userOffersList = UserOfferRepository.findByUserAndOfferBar(user.get(), bar.get()).stream()
           .map(x -> modelMapper.map(x, UserOfferDTO.class)).collect(Collectors.toList());

            ArrayList<SimpleUserOfferDTO> offers = new ArrayList<SimpleUserOfferDTO>();

           List<OfferDTO>  barOffersList = OfferRepository.findByBar(bar.get()).stream()
           .map(x -> modelMapper.map(x, OfferDTO.class)).collect(Collectors.toList());

           for(OfferDTO barOffer : barOffersList){
               if(barOffer.getEndDate().isBefore(LocalDate.now()) || barOffer.getStartDate().isAfter(LocalDate.now())){
                   break;
               }

               boolean isUserOffer =  false;
               for(UserOfferDTO userOffer : userOffersList){
                    if(barOffer.getIdOffer() == userOffer.getOffer().getIdOffer()){
                        isUserOffer = true;
                        offers.add(new SimpleUserOfferDTO(barOffer, userOffer.isUsed(), userOffer.getCode()));
                        break;
                    } 
               }
               if(!isUserOffer){
                    offers.add(new SimpleUserOfferDTO(barOffer, false, null));
               }
            }     
            for(SimpleUserOfferDTO offer : offers){
                offer.getOffer().getBar().setBarImages(null);
            }
           return offers;
        }
        return null;
    }

}
