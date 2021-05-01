package web.backend.gothere.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Entities.BarTableEntity;
import web.backend.gothere.Repositories.Interfaces.BarTableRepository;
import web.backend.gothere.Services.Models.BarTableDTO;

public class BarTableService {
    @Autowired
    private BarTableRepository barTableRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<BarTableDTO> getAll() {
        return barTableRepository.findAll().stream().map(x -> modelMapper.map(x, BarTableDTO.class))
                .collect(Collectors.toList());
    }

    public BarTableDTO add(BarTableDTO barTable) {
        BarTableEntity entityToUpdate = modelMapper.map(barTable, BarTableEntity.class);
        BarTableEntity result = barTableRepository.save(entityToUpdate);
        return modelMapper.map(result, BarTableDTO.class);
    }

    public BarTableDTO update(Long id, BarTableDTO barTable) {
        Optional<BarTableEntity> dataToUpdate = barTableRepository.findById(id);
        if (dataToUpdate.isPresent()) {

            BarTableEntity entityToUpdate = modelMapper.map(barTable, BarTableEntity.class);
            entityToUpdate.setIdBarTable(id);
            BarTableEntity result = barTableRepository.save(entityToUpdate);
            return modelMapper.map(result, BarTableDTO.class);

        }
        return null;
    }

    public void delete(Long idBarTable) {
        Optional<BarTableEntity> entityToDelete = barTableRepository.findById(idBarTable);
        if (entityToDelete.isPresent())
            barTableRepository.delete(entityToDelete.get());
    }

}
