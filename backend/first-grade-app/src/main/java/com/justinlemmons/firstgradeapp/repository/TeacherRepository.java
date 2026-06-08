package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.Teacher;
import com.justinlemmons.firstgradeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByUser(User user);


}
