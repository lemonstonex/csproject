package cs.project.evolt.service;

import cs.project.evolt.model.CarModel;
import cs.project.evolt.repository.CarModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    public Optional<CarModel> getCarModelById(Long id) {
        return carModelRepository.findById(id);
    }



}
