package dev.mahathir.expencetracker.service;

import dev.mahathir.expencetracker.entity.User;
import dev.mahathir.expencetracker.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
