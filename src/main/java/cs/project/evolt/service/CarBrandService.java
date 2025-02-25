package cs.project.evolt.service;

import cs.project.evolt.repository.CarBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarBrandService {

    private final CarBrandRepo carBrandRepo;

    @Autowired
    public CarBrandService(CarBrandRepo carBrandRepo){
        this.carBrandRepo = carBrandRepo;
    }

}
