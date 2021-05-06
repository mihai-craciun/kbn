package com.mihaicraciun.kbn.kbn.repositories;

import com.mihaicraciun.kbn.kbn.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}