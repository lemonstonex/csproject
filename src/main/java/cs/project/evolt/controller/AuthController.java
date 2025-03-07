package cs.project.evolt.controller;

import cs.project.evolt.DTO.LoginRequest;
import cs.project.evolt.Response.AuthResponse;
import cs.project.evolt.Response.ErrorResponse;
import cs.project.evolt.model.User;
import cs.project.evolt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        boolean isAuthenticated = userService.checkPassword(username, password);
//        if (isAuthenticated) {
//            return "Login successful";
//        } else {
//            return "Invalid username or password";
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.checkPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            User user = userService.getUserByUsername(loginRequest.getUsername());  // Assume you have this method
            return ResponseEntity.ok(new AuthResponse("Login successful", user));
        } else {
            return ResponseEntity.status(401).body(new ErrorResponse("Invalid username or password"));
        }
    }
}
