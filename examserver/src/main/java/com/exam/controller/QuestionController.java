package com.exam.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.model.exam.Question;
import com.exam.model.exam.Quizz;
import com.exam.service.QuestionService;
import com.exam.service.QuizzService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService service;

	@Autowired
	private QuizzService quizzService;

	/**
	 * add question
	 * 
	 * @param question
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(this.service.addQuestion(question));
	}

	/**
	 * update the question
	 * 
	 * @param question
	 * @return
	 */
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.service.updateQuestion(question));
	}

	/**
	 * get all question by quizId
	 * 
	 * @param qid
	 * @return
	 */
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
//		Quizz quiz = new Quizz();
//		quiz.setqId(qid);
//		Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuizz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);

		Quizz quiz = this.quizzService.getQuizz(qid);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);

	}

	/**
	 * get all question by quizId in Admin role
	 * 
	 * @param qid
	 * @return
	 */
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
		Quizz quiz = new Quizz();
		quiz.setqId(qid);
		Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuizz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);

	}

	/**
	 * get single question
	 * 
	 * @param quesId
	 * @return
	 */
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.service.getQuestion(quesId);
	}

	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.service.deleteQuesion(quesId);
	}

	/**
	 * eval quiz
	 * 
	 * @param questions
	 * @return
	 */
	@PostMapping("eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		System.out.println(questions);
		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		for (Question q : questions) {
//			single questions
			Question question = this.service.get(q.getQuesId());
			if (question.getAnswer().equals(q.getGivenAnswer())) {
//				correct
				correctAnswers++;

				double marksSingle = Double.parseDouble(questions.get(0).getQuizz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;
			}
			if (q.getGivenAnswer() != null ) {
				attempted++;
				System.out.println("AAA"+q);
			}
		}
		;
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers", correctAnswers
				, "attempted",attempted);
		return ResponseEntity.ok(map);
	}
}
