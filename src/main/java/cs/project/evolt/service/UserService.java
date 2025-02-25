package cs.project.evolt.service;

import cs.project.evolt.model.User;
import cs.project.evolt.repository.CarBrandRepo;
import cs.project.evolt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }




}
