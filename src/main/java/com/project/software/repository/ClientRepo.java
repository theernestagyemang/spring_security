package com.project.software.repository;

import com.project.software.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findByFullName(String fullName);
    @Query("SELECT count(u.id) FROM Client u")
    Integer getCompaniesCount();
}
