package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
