package com.savetheday.backend.db.repository;

import com.savetheday.backend.db.entity.AIResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AIResultRepository  extends JpaRepository<AIResult, Long> {
    Optional<AIResult> findByTaskDate(LocalDate date);
}
