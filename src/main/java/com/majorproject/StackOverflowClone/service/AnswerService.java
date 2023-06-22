package com.majorproject.StackOverflowClone.service;

import com.majorproject.StackOverflowClone.model.Answer;
import com.majorproject.StackOverflowClone.model.User;
import com.majorproject.StackOverflowClone.model.Comment;
import com.majorproject.StackOverflowClone.repository.AnswerRepository;
import com.majorproject.StackOverflowClone.repository.CommentRepository;
import com.majorproject.StackOverflowClone.repository.QuestionRepository;
import com.majorproject.StackOverflowClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CommentRepository commentRepository;
    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    public void updateAnswer(Answer answer){
        answer.setVotes((long) (answer.getVotedUpByUsers().size()-answer.getVotedDownByUsers().size()));
        answerRepository.save(answer);
    }

    public List<Answer> fetchAnswers(String sortBy) {
      return answerRepository.findAll(Sort.by(sortBy));
    }

    public void votedDown(Long answerId) {
        User user = userRepository.findById(1L).get();
        Answer answer = getAnswerById(answerId);
        User answerOwner = answer.getUser();

        if (answer.getVotedUpByUsers().contains(user)) {
            answerOwner.setReputation(answerOwner.getReputation()-10);
            answer.setUser(answerOwner);
            answer.getVotedUpByUsers().remove(user);
        } else {
            answerOwner.setReputation(answerOwner.getReputation()-5);
            answer.setUser(answerOwner);
            answer.getVotedDownByUsers().add(user);
        }
        updateAnswer(answer);
    }

    public void votedUp(Long answerId) {
        User user = userRepository.findById(1L).get();
        Answer answer = getAnswerById(answerId);
        User answerOwner = answer.getUser();

        if (answer.getVotedDownByUsers().contains(user)) {
            answerOwner.setReputation(answerOwner.getReputation()+5);
            answer.setUser(answerOwner);
            answer.getVotedDownByUsers().remove(user);
        } else {
            answerOwner.setReputation(answerOwner.getReputation()+10);
            answer.setUser(answerOwner);
            answer.getVotedUpByUsers().add(user);
        }
        updateAnswer(answer);
    }

    public void addAnswer(Long questionId, String data) {
        User user = userRepository.findById(1L).get();

        Answer answer = new Answer();
        answer.setAnswer(data);
        answer.setUser(user);
        answer.setQuestion(questionRepository.findById(questionId).get());
        answerRepository.save(answer);
    }

    public void addComment(Long answerId, String data) {
        User user = userRepository.findById(1L).get();

        Comment comment = new Comment();
        comment.setComment(data);
//        comment.setUser(user);
        commentRepository.save(comment);
         Answer answer = answerRepository.findById(answerId).get();
         answer.getComments().add(comment);
        answerRepository.save(answer);
    }
}
