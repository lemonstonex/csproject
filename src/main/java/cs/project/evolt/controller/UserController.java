package cs.project.evolt.controller;

import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import cs.project.evolt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    @PostMapping
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


}
