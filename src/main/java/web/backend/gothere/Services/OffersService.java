package web.backend.gothere.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.OfferEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Repositories.Interfaces.OffersRepository;
import web.backend.gothere.Services.Models.BarDTO;
import web.backend.gothere.Services.Models.OfferDTO;

public class OffersService {
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private BarRepository barRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<OfferDTO> getAll(Double latitude, Double length, Double distance) throws ResponseStatusException {
        List<OfferDTO> offersList = new ArrayList<OfferDTO>();
        if (latitude == null || length == null || distance == null) {
            offersList = offersRepository.findAll().stream().map(x -> modelMapper.map(x, OfferDTO.class))
                    .collect(Collectors.toList());
            if (!offersList.isEmpty()) {
                return offersList;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
            }
        }
        distance = transformDistance(distance);

        List<BarDTO> barList = barRepository.getByCoordinates(latitude, length, distance).stream()
                .map(x -> modelMapper.map(x, BarDTO.class)).collect(Collectors.toList());

        for (BarDTO barDTO : barList) {
            try {
                offersList.addAll(findOffersByBarId(barDTO.getIdbar()));
            } catch (Exception ex) {
                continue;
            }

        }

        if (!offersList.isEmpty()) {
            return offersList;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    public OfferDTO add(OfferDTO offer) {
        try {
            OfferEntity entityToInsert = modelMapper.map(offer, OfferEntity.class);
            offersRepository.save(entityToInsert);
            return offer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public OfferDTO addOfferImage(OfferDTO offer, MultipartFile multipartFile) {
        try {
            OfferEntity entityToInsert = modelMapper.map(offer, OfferEntity.class);
            offersRepository.save(entityToInsert);
            return offer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public Optional<OfferDTO> update(Long id, OfferDTO offer) {
        Optional<OfferEntity> dataToUpdate = offersRepository.findById(id);
        if (dataToUpdate.isPresent()) {
            if (dataToUpdate.get().getIdOffer() == id) {
                OfferEntity entityToUpdate = modelMapper.map(offer, OfferEntity.class);
                entityToUpdate.setIdOffer(id);
                OfferEntity result = offersRepository.save(entityToUpdate);
                return Optional.of(modelMapper.map(result, OfferDTO.class));
            }
        }
        return Optional.empty();
    }

    public OfferDTO findbyOfferId(Long id) throws ResponseStatusException {
        Optional<OfferEntity> entity = offersRepository.findById(id);
        if (entity.isPresent()) {
            return modelMapper.map(entity.get(), OfferDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<OfferDTO> findOffersByBarId(Long id) throws ResponseStatusException {

        Optional<BarEntity> bar = barRepository.findById(id);
        if (!bar.isPresent()) {
            throw new ElementNotFoundException();
        }
        List<OfferDTO> offersList = offersRepository.findByBar(bar.get()).stream()
                .map(x -> modelMapper.map(x, OfferDTO.class)).collect(Collectors.toList());

        if (!offersList.isEmpty()) {
            return offersList;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
    }

    public void deleteById(Long id) throws ResponseStatusException {
        Optional<OfferEntity> entityToDelete = offersRepository.findById(id);
        if (entityToDelete.isPresent()) {
            offersRepository.delete(entityToDelete.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error deletting data");
        }
    }

    static Double transformDistance(Double distance) {
        return (Double) distance / 100;
    }
}
