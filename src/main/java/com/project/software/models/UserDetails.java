package com.project.software.models;

import com.project.software.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String firstname;
    private String lastname;
    private String otherName;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @Enumerated(EnumType.STRING)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
