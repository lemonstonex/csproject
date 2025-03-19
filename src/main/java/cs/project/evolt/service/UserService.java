package cs.project.evolt.service;

import cs.project.evolt.DTO.UserProfileDTO;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import io.jsonwebtoken.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

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

    public boolean checkPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
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

    public boolean updateUserProfile(long userId, UserProfileDTO userProfileUpdateDTO) {
        Optional<User> userOptional = userRepository.findById(String.valueOf(userId));

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String newPassword = userProfileUpdateDTO.getPassword();
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                String hashedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(hashedPassword);
            }

            String newUsername = userProfileUpdateDTO.getUsername();
            if (newUsername != null && !newUsername.trim().isEmpty()) {
                user.setUsername(newUsername);
            }

            String newEmail = userProfileUpdateDTO.getEmail();
            if (newEmail != null && !newEmail.trim().isEmpty()) {
                user.setEmail(newEmail);
            }

            Long newModelId = userProfileUpdateDTO.getModelId();
            if (newModelId != null) {
                user.setModel_id(newModelId);
            }

            userRepository.save(user);
            return true;
        }

        return false;
    }




    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
