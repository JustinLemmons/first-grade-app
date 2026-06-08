package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Student;
import com.justinlemmons.firstgradeapp.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {

    List<StudentAssignment> findByStudent(Student student);
}
