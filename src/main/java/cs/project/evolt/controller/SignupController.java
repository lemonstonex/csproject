package cs.project.evolt.controller;

import cs.project.evolt.Request.SignUpRequest;
import cs.project.evolt.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class SignupController {
    @Autowired
    private SignUpService signupService;


    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody SignUpRequest request) {
        if (!signupService.isEmailAvailable(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        signupService.createUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

}
