package cs.project.evolt.controller;

import cs.project.evolt.DTO.UserProfileDTO;
import cs.project.evolt.model.Reviews;
import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import cs.project.evolt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            if (!userService.isEmailAvailable(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already taken");
            }

            User savedUser = userService.addUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping(value = "/list")
    public List<User> getUsers(){
        return userRepository.findAll();
    }



    @PutMapping("/profile/{user_id}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable long user_id,
            @RequestBody UserProfileDTO userProfileDTO) {

        boolean success = userService.updateUserProfile(user_id, userProfileDTO);

        if (success) {
            return ResponseEntity.ok("updated success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


}
