package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quizz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuizz(Quizz quizz);

}
