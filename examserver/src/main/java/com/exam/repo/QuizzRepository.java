package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quizz;

public interface QuizzRepository extends JpaRepository<Quizz, Long> {
	public List<Quizz> findByCategory(Category category);
	
	public List<Quizz> findByActtive(Boolean b);
	
	public List<Quizz> findByCategoryAndActtive(Category c, Boolean b);
}
