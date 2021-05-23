package web.backend.gothere.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import web.backend.gothere.FileUploadUtil;
import web.backend.gothere.Exceptions.ElementNotFoundException;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Entities.BarImgsEntity;
import web.backend.gothere.Repositories.Interfaces.BarImgsRepository;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Services.Models.BarDTO;
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

    public BarImgsDTO add(String fileName, Long idBar, MultipartFile multipartFile){
        Optional<BarEntity> bar = barRepository.findById(idBar);
        if(!bar.isPresent()){
            return null;
        }
        BarImgsDTO barImage = new BarImgsDTO("/images/bars/" + bar.get().getIdBar() + "/" + fileName, modelMapper.map( bar.get(), BarDTO.class));
        try {
            String uploadDir = "src/main/resources/static/images/bars/" + bar.get().getIdBar();
 
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            BarImgsEntity entityToInsert = modelMapper.map(barImage, BarImgsEntity.class);
            //TODO esto está mal, lo hice así por que me cogia el id del bar como null
            entityToInsert.getBar().setIdBar(bar.get().getIdBar());
            barImgsRepository.save(entityToInsert);
            return barImage;
        } catch (Exception e) {
            System.out.println( e.getCause());
            return null;
        }
        
       
    }

    public Optional<BarImgsDTO> update(Long id, BarImgsDTO image){
        Optional<BarImgsEntity> dataToUpdate = barImgsRepository.findById(id);
        if(dataToUpdate.isPresent()){
            if(dataToUpdate.get().getIdImgBar() == id){
                BarImgsEntity entityToUpdate = modelMapper.map(image, BarImgsEntity.class);
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
