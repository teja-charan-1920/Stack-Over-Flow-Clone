package com.majorproject.StackOverflowClone.repository;

import com.majorproject.StackOverflowClone.model.Tag;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
    Tag findByName(String trimmedTag);

    @Query("DELETE FROM Tag t WHERE t NOT IN (SELECT DISTINCT tag FROM Question q JOIN q.tags tag)")
    @Modifying
    @Transactional
    void deleteUnusedTags();
}
