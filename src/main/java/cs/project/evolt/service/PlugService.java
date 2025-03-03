package cs.project.evolt.service;
import cs.project.evolt.model.Plug;
import cs.project.evolt.repository.PlugRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PlugService {
    @Autowired
    private PlugRepository plugRepository;


    public List<Plug> getAllPlugs() {
        return plugRepository.findAll();
    }


}
