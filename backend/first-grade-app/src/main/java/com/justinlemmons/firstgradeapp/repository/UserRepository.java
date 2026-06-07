package com.justinlemmons.firstgradeapp.repository;

import com.justinlemmons.firstgradeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
