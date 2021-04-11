package web.backend.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.Repositories.Entities.OfferEntity;
import web.backend.Repositories.Interfaces.OffersRepository;
import web.backend.Services.Models.OfferDTO;

public class OffersService {
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private ModelMapper modelMappper;

    public List<OfferDTO> getAll() throws ResponseStatusException {
        
        List<OfferDTO> offersList = offersRepository.findAll().stream()
        .map(x -> modelMappper.map(x, OfferDTO.class))
        .collect(Collectors.toList());

        if(!offersList.isEmpty()){
            return offersList;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    public OfferDTO add(OfferDTO offer){
        OfferEntity entityToInsert = modelMappper.map(offer, OfferEntity.class);
        OfferEntity result = offersRepository.save(entityToInsert);
        return modelMappper.map(result, OfferDTO.class);
    }

    public Optional<OfferDTO> update(Long ID, OfferDTO offer){
        Optional<OfferEntity> dataToUpdate = offersRepository.findById(ID);
        if(dataToUpdate.isPresent()){
            if(dataToUpdate.get().getIdOffer() == ID){
                OfferEntity entityToUpdate = modelMappper.map(offer, OfferEntity.class);
                entityToUpdate.setIdOffer(ID);
                OfferEntity result = offersRepository.save(entityToUpdate);
                return Optional.of(modelMappper.map(result, OfferDTO.class));
            }
        }
        return Optional.empty();
    }

    public OfferDTO findbyOfferId(Long id) throws ResponseStatusException{
        Optional<OfferEntity> entity = offersRepository.findById(id);
        if(entity.isPresent()){
            return modelMappper.map(entity.get(), OfferDTO.class);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }


    public void delete(Long ID) throws ResponseStatusException{
        Optional<OfferEntity> entityToDelete = offersRepository.findById(ID);
        if(entityToDelete.isPresent()){
            offersRepository.delete(entityToDelete.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error deletting new data");
        }
    }

    public void deleteByOrderId(Long id) throws ResponseStatusException {

        Optional<OfferEntity> entityToDelete = offersRepository.findById(id);
        
        if(entityToDelete.isPresent()){
            offersRepository.deleteById(entityToDelete.get().getIdOffer());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error deletting new data"); 
        }
    }
}
