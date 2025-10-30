package com.Ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.Ecommerce.entity.Question;
import com.Ecommerce.entity.Test;
import com.Ecommerce.repository.QuestionRepo;
import com.Ecommerce.repository.TestRepo;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private TestRepo testRepo;

    // Add Question and update cache
    @CacheEvict(value = "questions", key = "#testId") // Clear cache for this test after new question
    public Question addQuestion(Long testId, Question question) {
        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not Found!"));
        question.setTest(test);
        return questionRepo.save(question);
    }

    // Fetch all questions by testId (cache it)
    @Cacheable(value = "questions", key = "#testId")
    public List<Question> showQuestionByTestId(Long testId) {
        System.out.println("Fetching from DB (not cache)..."); // for debug
        return questionRepo.findAllByTestId(testId);
    }

    // Update Question and update cache
    @CachePut(value = "question", key = "#questionId")
    public Question updateQuestion(Long questionId, Question updateQuestion) {
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question does not Found!"));

        question.setQuestionText(updateQuestion.getQuestionText());
        question.setOptionA(updateQuestion.getOptionA());
        question.setOptionB(updateQuestion.getOptionB());
        question.setOptionC(updateQuestion.getOptionC());
        question.setOptionD(updateQuestion.getOptionD());
        question.setCorrectAnswer(updateQuestion.getCorrectAnswer());

        return questionRepo.save(question);
    }

    // Delete Question and evict from cache
    @CacheEvict(value = {"question", "questions"}, allEntries = true)
    public String deleteQuestion(Long questionId) {
        questionRepo.deleteById(questionId);
        return "Delete Successfully!";
    }
}
