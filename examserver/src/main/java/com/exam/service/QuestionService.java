package com.exam.service;

import java.util.Set;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quizz;

public interface QuestionService {

	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long qId);
	
	public Set<Question> getQuestionsOfQuizz(Quizz quizz);
	
	public void deleteQuesion(long qId);
	
	public Question get(Long questionId);
}
