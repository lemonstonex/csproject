package cs.project.evolt.service;
import cs.project.evolt.DTO.PlugRequest;
import cs.project.evolt.model.Plug;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.repository.PlugRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PlugService {
    @Autowired
    private PlugRepository plugRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Plug> getAllPlugs() {
        return plugRepository.findAll();
    }

    public void savePlug(PlugRequest plug) {
        Plug new_plug = modelMapper.map(plug, Plug.class);
        plugRepository.save(new_plug);
    }


}
