package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Student;
import com.justinlemmons.firstgradeapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByTeacher(Teacher teacher);
}
