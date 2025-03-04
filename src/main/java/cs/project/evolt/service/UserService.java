package cs.project.evolt.service;

import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.findByEmail(email) == null;
    }


    public User addUser(User user) {
        User record = modelMapper.map(user, User.class);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);
        record.setRole("ROLE_USER");
        record.setModel_id(user.getModel_id());
        userRepository.save(record);
        return record;
    }


}
