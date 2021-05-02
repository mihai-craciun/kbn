package com.mihaicraicun.kbn.kbn.repositories;

import java.util.List;

import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.model.WorksOn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorksOnRepository extends JpaRepository<WorksOn, Long> {
    List<WorksOn> findByUser(User user);
}
