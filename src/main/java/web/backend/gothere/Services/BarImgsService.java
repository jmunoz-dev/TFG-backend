package web.backend.gothere.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarImgsEntity;
import web.backend.gothere.Repositories.Interfaces.BarImgsRepository;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Services.Models.BarImgsDTO;

public class BarImgsService {
    @Autowired
    private BarImgsRepository barImgsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BarRepository barRepository;

    public List<BarImgsDTO> getAll(){
    List<BarImgsDTO> barImgsList = barImgsRepository.findAll().stream()
        .map(x -> modelMapper.map(x, BarImgsDTO.class))
        .collect(Collectors.toList());

        if(!barImgsList.isEmpty()){
            return barImgsList;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding data");
        }
    }

    public BarImgsDTO add(BarImgsDTO offer){
        try {
            BarImgsEntity entityToInsert = modelMapper.map(offer, BarImgsEntity.class);
            barImgsRepository.save(entityToInsert);
            return offer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        
       
    }

    public Optional<BarImgsDTO> update(Long id, BarImgsDTO offer){
        Optional<BarImgsEntity> dataToUpdate = barImgsRepository.findById(id);
        if(dataToUpdate.isPresent()){
            if(dataToUpdate.get().getIdImgBar() == id){
                BarImgsEntity entityToUpdate = modelMapper.map(offer, BarImgsEntity.class);
                entityToUpdate.setIdImgBar(id);
                BarImgsEntity result = barImgsRepository.save(entityToUpdate);
                return Optional.of(modelMapper.map(result, BarImgsDTO.class));
            }
        }
        return Optional.empty();
    }

    public BarImgsDTO findbyImgId(Long id) throws ResponseStatusException{
        Optional<BarImgsEntity> entity = barImgsRepository.findById(id);
        if(entity.isPresent()){
            return modelMapper.map(entity.get(), BarImgsDTO.class);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<BarImgsDTO> findImgsByBarId(Long id) throws ResponseStatusException{

        Optional<BarEntity> bar = barRepository.findById(id);
        if(!bar.isPresent()){
            throw new ElementNotFoundException();
        }
        List<BarImgsDTO> barImgsList = barImgsRepository.findByBar(bar.get()).stream()
        .map(x -> modelMapper.map(x, BarImgsDTO.class))
        .collect(Collectors.toList());

        if(!barImgsList.isEmpty()){
            return barImgsList;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        }
    }


    public void deleteById(Long id) throws ResponseStatusException{
        Optional<BarImgsEntity> entityToDelete = barImgsRepository.findById(id);
        if(entityToDelete.isPresent()){
            barImgsRepository.delete(entityToDelete.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error deletting data");
        }
    }






}
