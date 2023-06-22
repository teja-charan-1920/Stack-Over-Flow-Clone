package com.majorproject.StackOverflowClone.repository;

import com.majorproject.StackOverflowClone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
