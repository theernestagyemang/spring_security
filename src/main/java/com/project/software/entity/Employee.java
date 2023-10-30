package com.project.software.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String firstName;
    private String surName;
    private String otherNames;
    private String fullName;
    private String currentAddress;
    private String phoneContact;
    private String emergencyContact;
    private String email;
    private String gender;
    private String age;
    private String nationality;
    private String languagesSpoken;
    private String role;
    private String imageFileName;
    private String imageFileType;
    private String professionalTitle;
    private String occupation;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date entryDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
