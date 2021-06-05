package web.backend.gothere.Services;

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

    public BarImgsDTO add( Long idBar, MultipartFile multipartFile){
        Optional<BarEntity> bar = barRepository.findById(idBar);
        if(!bar.isPresent()){
            return null;
        } 
       
        String originalName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = FileUploadUtil.getFileName(originalName, bar.get().getName() + " " + (barImgsRepository.hashCode()));

        BarImgsDTO barImage = new BarImgsDTO("/images/bars/" + bar.get().getIdBar() + "/" + fileName, modelMapper.map( bar.get(), BarDTO.class));
        try {

            BlobContainerClient container = new BlobContainerClientBuilder()
                    .connectionString(Constants.CON_STR_AZURE)
                    .containerName("images/bars/" + bar.get().getIdBar() )
                    .buildClient();
    
    
            BlobClient blob=container.getBlobClient(fileName);
            blob.upload(multipartFile.getInputStream(),multipartFile.getSize(),true);

            BarImgsEntity entityToInsert = modelMapper.map(barImage, BarImgsEntity.class);
            entityToInsert.getBar().setIdBar(bar.get().getIdBar());
            barImgsRepository.save(entityToInsert);
            return barImage;
        } catch (Exception e) {
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
            String url = entityToDelete.get().getImgUrl();
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
            }catch(Exception ioe){
                throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Error deletting file");
            }
            barImgsRepository.delete(entityToDelete.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error deletting data");
        }
    }






}
