package web.backend.gothere.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import web.backend.gothere.Repositories.Interfaces.BarImgsRepository;

public class BarImgsService {
    @Autowired
    private BarImgsRepository barImgsRepository;
    @Autowired
    private ModelMapper modelMapper;
}
