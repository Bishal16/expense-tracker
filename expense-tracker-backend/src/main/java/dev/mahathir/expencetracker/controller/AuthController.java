package dev.mahathir.expencetracker.controller;


import dev.mahathir.expencetracker.DTO.LoginRequest;
import dev.mahathir.expencetracker.entity.User;
import dev.mahathir.expencetracker.Security.JwtService;
import dev.mahathir.expencetracker.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final UserRegistrationService userRegistrationService;
    private final JwtService jwtService;

    public AuthController(UserRegistrationService userRegistrationService, JwtService jwtService ) {
        this.userRegistrationService = userRegistrationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userRegistrationService.registerNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRegistrationService.authenticateUser(loginRequest);
        String jwtToken = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok(jwtToken);
    }
}
