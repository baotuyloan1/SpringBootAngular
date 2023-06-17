package com.exam.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quizz;

public interface QuizzService {
	public Quizz addQuizz(Quizz quizz);
	
	public Quizz updateQuizz(Quizz quizz);
	

	public Set<Quizz> getQuizzes();
	
	public Quizz getQuizz(Long quizzId);
	
	@Modifying
    @Transactional
	@Query(value = "DELETE FROM quizz WHERE qid = ?1", nativeQuery = true)
	public void deleteQuizz(Long qid);


	public List<Quizz> getQuizzesOfCategory(Category category);
	
	public List<Quizz> getActiveQuizzes();
	
	public List<Quizz> getActiveQuizzesOfCategory(Category c);
	
	
}
