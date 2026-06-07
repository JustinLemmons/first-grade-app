package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
