package com.onlinebookstore.onlinebookstore.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Constructors, getters, and setters
}
