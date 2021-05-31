package web.backend.gothere.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Interfaces.ScheduleRepository;
import web.backend.gothere.Services.Models.ScheduleDTO;

public class SchedulesService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ScheduleDTO> getAll() {
        List<ScheduleDTO> schedules =  scheduleRepository.findAll().stream()
        .map(x -> modelMapper.map(x, ScheduleDTO.class))
        .collect(Collectors.toList());
        return schedules;        
    }
}
