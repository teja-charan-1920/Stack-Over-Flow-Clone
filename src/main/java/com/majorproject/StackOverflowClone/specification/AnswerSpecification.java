package com.majorproject.StackOverflowClone.specification;

import com.majorproject.StackOverflowClone.model.Answer;
import org.springframework.data.jpa.domain.Specification;

public class AnswerSpecification {
    public Specification<Answer> findByQuestionIdAndSortByVotes(Long questionId, String sortBy) {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get(sortBy)));
            return criteriaBuilder.equal(root.get("question").get("id"), questionId);
        };
    }
}
