package com.exam.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quizz;
import com.exam.repo.QuestionRepository;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<Question>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long qId) {
		return this.questionRepository.findById(qId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuizz(Quizz quizz) {
		return this.questionRepository.findByQuizz(quizz);
	}

	@Override
	public void deleteQuesion(long qId) {
		Question question = new Question();
		question.setQuesId(qId);
		this.questionRepository.delete(question);
	}

	@Override
	public Question get(Long questionId) {
		return this.questionRepository.getOne(questionId);
	}

}
