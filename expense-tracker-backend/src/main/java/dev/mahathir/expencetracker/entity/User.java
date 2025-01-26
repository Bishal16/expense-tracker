package dev.mahathir.expencetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    @Email (message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @Size(min = 3, max = 20, message = "userName must be between 3 to 50 character")
    private String userName;

    @Column(nullable = false)
    @Size(min = 8, message = "password must be at least 8 characters long")
    private String password;


}
