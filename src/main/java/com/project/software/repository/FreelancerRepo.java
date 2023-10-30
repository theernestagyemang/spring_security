package com.project.software.repository;

import com.project.software.entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepo extends JpaRepository<Freelancer, Integer> {
}
