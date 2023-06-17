package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quizz;
import com.exam.service.QuizzService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizzService quizzService;

	/**
	 * add quizz
	 * 
	 * @param quizz
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<Quizz> add(@RequestBody Quizz quizz) {
		return ResponseEntity.ok(this.quizzService.addQuizz(quizz));
	}

	/**
	 * update quizz
	 * 
	 * @param quizz
	 * @return
	 */

	@PutMapping("/")
	public ResponseEntity<Quizz> update(@RequestBody Quizz quizz) {
		return ResponseEntity.ok(this.quizzService.updateQuizz(quizz));
	}

	/**
	 * get quizz list
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.quizzService.getQuizzes());
	}

	/**
	 * get single quiz
	 * 
	 * @param qid
	 * @return
	 */
	@GetMapping("/{qid}")
	public Quizz quizz(@PathVariable("qid") Long qid) {
		return this.quizzService.getQuizz(qid);
	}

	/**
	 * delete the quizz
	 * 
	 * @param qid
	 */
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
		this.quizzService.deleteQuizz(qid);
	}

	/**
	 * 
	 * get quizes of category
	 * 
	 * @param cid
	 * @return
	 */
	@GetMapping("/category/{cid}")
	public List<Quizz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.quizzService.getQuizzesOfCategory(category);
	}

	/**
	 * get active quizzes
	 * 
	 * @return
	 */
	@GetMapping("/active")
	public List<Quizz> getActiveQuizzes() {
		return this.quizzService.getActiveQuizzes();
	}

	@GetMapping("/category/active/{cid}")
	public List<Quizz> getActiveQuizzes(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCid(cid);
		return this.quizzService.getActiveQuizzesOfCategory(category);
	}
}
