package cs.project.evolt.service;

import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUpService {

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;




}
