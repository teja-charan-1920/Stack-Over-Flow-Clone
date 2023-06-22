package com.majorproject.StackOverflowClone.repository;

import com.majorproject.StackOverflowClone.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
