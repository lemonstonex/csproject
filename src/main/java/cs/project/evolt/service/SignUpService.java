package cs.project.evolt.service;

import cs.project.evolt.Request.SignUpRequest;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isEmailAvailable(String email) {
        return userRepository.findByEmail(email) == null;
    }

    public void createUser(SignUpRequest user) {
        User record = modelMapper.map(user, User.class);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);
        record.setRole("ROLE_USER");
        record.setModel_id(user.getModel_id());
        userRepository.save(record);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }




}
