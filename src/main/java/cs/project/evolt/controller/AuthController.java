package cs.project.evolt.controller;

import cs.project.evolt.DTO.LoginRequest;
import cs.project.evolt.Response.AuthResponse;
import cs.project.evolt.Response.ErrorResponse;
import cs.project.evolt.model.User;
import cs.project.evolt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        boolean isExisted = userService.isEmailAvailable(loginRequest.getEmail());
        // ถ้าเมลว่าง = email ไม่เคยลงทะเบียน = never exists
        if (isExisted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found"));
        }
        User user = userService.getUserByEmail(loginRequest.getEmail()); // fetch user from email

        boolean isAuthenticated = userService.checkPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid password"));
        }


        return ResponseEntity.ok(new AuthResponse("Login successful", user));
    }
}
