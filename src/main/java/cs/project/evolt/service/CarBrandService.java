package cs.project.evolt.service;

import cs.project.evolt.model.CarBrand;
import cs.project.evolt.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    public List<CarBrand> getAllCarBrands() {
        return carBrandRepository.findAll();
    }

}
