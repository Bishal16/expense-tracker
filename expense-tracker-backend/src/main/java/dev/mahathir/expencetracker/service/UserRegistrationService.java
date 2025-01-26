package dev.mahathir.expencetracker.service;

import dev.mahathir.expencetracker.DTO.LoginRequest;
import dev.mahathir.expencetracker.entity.User;
import dev.mahathir.expencetracker.repository.UserRegisterRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserRegistrationService {
    private final UserRegisterRepo userRegisterRepo;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRegisterRepo userRegisterRepo) {
        this.userRegisterRepo = userRegisterRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerNewUser(User user){
        if(userRegisterRepo.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRegisterRepo.save(user);
    }

    public User authenticateUser(LoginRequest loginRequest) {
        User user = userRegisterRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid email or password");
        }
    return user;
    }
}
