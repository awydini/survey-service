package net.aydini.ml.surveyservice.domain.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.aydini.ml.surveyservice.domain.util.CustomPage;

public interface QuestionService {

	
	
	Question findById(Long questionId);
	
	
	List<Question> findAll();
	
	
	Page<Question> findAll(Pageable page);
	
	
	void create(Question question);
	
	void update(Question question);
	
	
	void delete(Long questionId);
	
	
	void updateEnabled(Long id, Boolean enabled);
	
	
	CustomPage<Question> findSurveyQuestions(Long surveyId,Pageable pageable);
}
