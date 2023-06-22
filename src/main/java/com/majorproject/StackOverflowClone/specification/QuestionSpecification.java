package com.majorproject.StackOverflowClone.specification;

import com.majorproject.StackOverflowClone.model.Question;
import com.majorproject.StackOverflowClone.model.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class QuestionSpecification {
    public Specification<Question> filterPostsOnKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            String lowercaseKeyword = "%" + keyword.toLowerCase() + "%";
            Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), lowercaseKeyword);
            Predicate descriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), lowercaseKeyword);

            Subquery<Long> tagSubquery = query.subquery(Long.class);
            Root<Question> tagSubqueryRoot = tagSubquery.from(Question.class);
            Join<Question, Tag> tagJoin = tagSubqueryRoot.join("tags");
            tagSubquery.select(tagSubqueryRoot.get("id"))
                    .where(criteriaBuilder.like(criteriaBuilder.lower(tagJoin.get("name")), lowercaseKeyword));
            Predicate tagPredicate = criteriaBuilder.in(root.get("id")).value(tagSubquery);

            return criteriaBuilder.or(descriptionPredicate, titlePredicate, tagPredicate);
        };
    }

    public Specification<Question> getQuestionsInLastDays(int days) {
        return (root, query, criteriaBuilder) -> {
            LocalDateTime lastHrsQuestions = LocalDateTime.now().minusDays(days);
            return criteriaBuilder.greaterThan(root.get("createdAt"), lastHrsQuestions);
        };
    }
}
