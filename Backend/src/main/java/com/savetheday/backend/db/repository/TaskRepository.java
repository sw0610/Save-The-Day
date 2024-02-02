package com.savetheday.backend.db.repository;

import com.savetheday.backend.db.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findAllByMemberIdAndDueDateAndProcessStatusOrderByCreatedAt(Long memberId, LocalDate date, String status);
    Optional<Task> findByTaskId(Long taskId);

}
