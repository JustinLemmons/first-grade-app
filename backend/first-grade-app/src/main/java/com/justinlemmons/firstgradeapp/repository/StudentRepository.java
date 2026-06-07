package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
