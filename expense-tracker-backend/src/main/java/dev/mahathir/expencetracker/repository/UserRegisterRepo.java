package dev.mahathir.expencetracker.repository;


import dev.mahathir.expencetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegisterRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
