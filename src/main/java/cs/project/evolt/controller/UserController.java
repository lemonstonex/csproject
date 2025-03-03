package cs.project.evolt.controller;

import cs.project.evolt.model.User;
import cs.project.evolt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody User user) {
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User saved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "update/{user_id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable long user_id, @RequestBody User user){
        User updatedUser = userRepository.findById(String.valueOf(user_id)).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        userRepository.save(updatedUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

        // test postman only naja
    }

}
