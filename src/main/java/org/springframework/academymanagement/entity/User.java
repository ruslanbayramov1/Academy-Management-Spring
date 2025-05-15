package org.springframework.academymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 64)
    private String firstName;

    @Column(nullable = false, length = 64)
    private String lastName;

    @Column(unique = true, nullable = false, length = 32)
    private String username;

    @Column(unique = true, nullable = false, length = 312)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
}
