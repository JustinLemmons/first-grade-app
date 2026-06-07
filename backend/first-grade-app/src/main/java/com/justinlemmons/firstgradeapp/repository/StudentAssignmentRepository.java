package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {
}
