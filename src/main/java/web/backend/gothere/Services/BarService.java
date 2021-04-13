package web.backend.gothere.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Interfaces.BarRepository;
import web.backend.gothere.Services.Models.BarDTO;

public class BarService {
    @Autowired
    private BarRepository barRepository;
    @Autowired
    private ModelMapper modelMapper;
  
    public List<BarDTO> getAll() {
          return barRepository.findAll().stream().map(x -> modelMapper.map(x, BarDTO.class))
              .collect(Collectors.toList());
        }
}
