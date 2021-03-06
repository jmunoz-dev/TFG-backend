package web.backend.gothere.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import web.backend.gothere.Repositories.Entities.BarEntity;
import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Services.Models.BarDTO;
import web.backend.gothere.Services.Models.BarImgsDTO;

public class BarService {
    @Autowired
    private BarRepository barRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<BarDTO> getAll() {
        return deleteBarImage(barRepository.findAll().stream().map(x -> modelMapper.map(x, BarDTO.class)).collect(Collectors.toList()));
    }

    // delete
    public void delete(Long idbar) {
        Optional<BarEntity> entityToDelete = barRepository.findById(idbar);
        if (entityToDelete.isPresent())
            barRepository.delete(entityToDelete.get());
    }

    // update
    public BarDTO update(Long id, BarDTO bar) {
        Optional<BarEntity> dataToUpdate = barRepository.findById(id);
        if (dataToUpdate.isPresent()) {

            BarEntity entityToUpdate = modelMapper.map(bar, BarEntity.class);
            entityToUpdate.setIdBar(id);
            BarEntity result = barRepository.save(entityToUpdate);
            return modelMapper.map(result, BarDTO.class);

        }
        return null;
    }

    // add
    public BarDTO add(BarDTO bar) {
        BarEntity entityToUpdate = modelMapper.map(bar, BarEntity.class);
        BarEntity result = barRepository.save(entityToUpdate);
        return modelMapper.map(result, BarDTO.class);
    }

    // get By Id
    public BarDTO getBarById(Long id) {
        Optional<BarEntity> barById = barRepository.findById(id);
        if (barById.isPresent()) {
            BarDTO result = modelMapper.map(barById.get(), BarDTO.class);
            for(BarImgsDTO image : result.getBarImages()){
                image.setBar(null);
            }
            return result;
        } else {

            return null;
        }

    }
    public List<BarDTO> getByCoordinates(double length, double latitude, double distance){
        distance = OffersService.transformDistance(distance);
        List<BarDTO> barsList =barRepository.getByCoordinates(latitude, length, distance).stream()
        .map(x -> modelMapper.map(x, BarDTO.class))
        .collect(Collectors.toList());

        return deleteBarImage(barsList);
    }
    public List<BarDTO> getByNameOrAddress(String query){
        query = query.trim().toUpperCase();
        List<BarDTO> barsList =barRepository.findByNameOrAddress(query,query).stream()
        .map(x -> modelMapper.map(x, BarDTO.class))
        .collect(Collectors.toList());

        return deleteBarImage(barsList);
    }

    private List<BarDTO> deleteBarImage(List<BarDTO> list){
        for(BarDTO bar : list){
            for(BarImgsDTO image : bar.getBarImages()){
                image.setBar(null);
            }
        }
        return list;
    }
    

}
