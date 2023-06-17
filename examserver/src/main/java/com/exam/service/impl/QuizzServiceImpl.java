package com.exam.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quizz;
import com.exam.repo.QuizzRepository;
import com.exam.service.QuizzService;

@Service
public class QuizzServiceImpl implements QuizzService {

	@Autowired
	private QuizzRepository quizzRepository;
	
	@Override
	public Quizz addQuizz(Quizz quizz) {
		return this.quizzRepository.save(quizz);
	}

	@Override
	public Quizz updateQuizz(Quizz quizz) {
		return this.quizzRepository.save(quizz);
	}

	@Override
	public Set<Quizz> getQuizzes() {
		return new HashSet<Quizz>(this.quizzRepository.findAll());
	}

	@Override
	public Quizz getQuizz(Long quizzId) {
		return this.quizzRepository.findById(quizzId).get();
	}

	@Override
	public void deleteQuizz(Long quizzId) {
//		Quizz quizz = new Quizz();
//		quizz.setqId(quizzId);
//		this.quizzRepository.delete(quizz);
		this.quizzRepository.deleteById(quizzId);
	}

	

	@Override
	public List<Quizz> getQuizzesOfCategory(Category category) {
		return this.quizzRepository.findByCategory(category);
	}

	/**
	 * get active quizzes
	 */
	@Override
	public List<Quizz> getActiveQuizzes() {
		return this.quizzRepository.findByActtive(true);
	}

	@Override
	public List<Quizz> getActiveQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizzRepository.findByCategoryAndActtive(c, true);
	}
	
	

}
