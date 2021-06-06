package web.backend.gothere.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Constants;
import web.backend.gothere.FileUploadUtil;
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
        if (latitude == null || length == null || distance == null || length == 0 || latitude == 0) {
            offersList = offersRepository.findAll().stream().map(x -> modelMapper.map(x, OfferDTO.class))
                    .collect(Collectors.toList());
            if (!offersList.isEmpty()) {
                for(OfferDTO offer : offersList){
                    offer.getBar().setBarImages(null);
                }
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
            for(OfferDTO offer : offersList){
                offer.getBar().setBarImages(null);
            }
            return offersList;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    public OfferDTO add(OfferDTO offer) {
        try {
            OfferEntity entityToInsert = modelMapper.map(offer, OfferEntity.class);
            OfferEntity result = offersRepository.save(entityToInsert);
            
            return modelMapper.map(result, OfferDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public OfferDTO addOfferImage(OfferDTO offer, MultipartFile multipartFile) {
        
            String originalName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String fileName = FileUploadUtil.getFileName(originalName, offer.getBar().getName() + " " +"offer"+offer.getIdOffer());
       
            try {

                BlobContainerClient container = new BlobContainerClientBuilder()
                        .connectionString(Constants.CON_STR_AZURE)
                        .containerName("images/offers/bar" + offer.getBar().getIdbar())
                        .buildClient();
        
        
                BlobClient blob=container.getBlobClient(fileName);
                blob.upload(multipartFile.getInputStream(),multipartFile.getSize(),true);
    
                OfferEntity entityToInsert = modelMapper.map(offer, OfferEntity.class);
                entityToInsert.setOfferImage("/images/offers/bar"+offer.getBar().getIdbar() + "/" + fileName);
                offersRepository.save(entityToInsert);
                return offer;
            } catch (Exception e) {
                return null;
            }
    }

    public void deleteOfferImage(OfferDTO offer) throws ResponseStatusException{
        Optional<OfferEntity> entityToDelete = offersRepository.findById(offer.getIdOffer());
        if(entityToDelete.isPresent()){
            String url = entityToDelete.get().getOfferImage();
            int pos = url.lastIndexOf("/");
            String fileName = url.substring(pos + 1, url.length());
            url = url.substring(1, pos);
            try{
                 BlobContainerClient container = new BlobContainerClientBuilder()
                    .connectionString(Constants.CON_STR_AZURE)
                    .containerName(url)
                    .buildClient();

                BlobClient blob=container.getBlobClient(fileName);
                blob.delete();
                entityToDelete.get().setOfferImage(null);
                OfferEntity entityToInsert = modelMapper.map(offer, OfferEntity.class);
                entityToInsert.setOfferImage(null);
                offersRepository.save(entityToInsert);
            }catch(Exception ioe){
                throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error deletting file");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding data");
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

        for(OfferDTO offer : offersList){
            offer.getBar().setBarImages(null);
        }
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
