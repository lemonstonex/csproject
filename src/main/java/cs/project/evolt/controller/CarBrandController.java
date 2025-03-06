package cs.project.evolt.controller;

import cs.project.evolt.model.CarBrand;
import cs.project.evolt.model.Station;
import cs.project.evolt.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carbrand")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @GetMapping("/list")
    public List<CarBrand> getCarBrand(){
        System.out.println("Fetching all car brand...");
        return carBrandService.getAllCarBrands();
    }
}
