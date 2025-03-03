package cs.project.evolt.controller;

import cs.project.evolt.Request.SignUpRequest;
import cs.project.evolt.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public class SignupController {
    @Autowired
    private SignUpService signupService;


    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody SignUpRequest request) {
        if (!signupService.isEmailAvailable(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }

        signupService.createUser(request);
        return ResponseEntity.ok("User registered successfully!");
    }

}
