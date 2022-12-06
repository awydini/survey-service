package net.aydini.ml.surveyservice.domain.survey;

import java.util.List;

import org.springframework.data.domain.Pageable;

import net.aydini.ml.surveyservice.domain.util.CustomPage;

public interface SurveyService {

	
	
	Survey findById(Long surveyId);
	
	
	List<Survey> findAll();
	
	CustomPage<Survey> findAll(Pageable pageable);
	
	void create(Survey survey);
	
	
	void update(Survey survey);
	
	
	void delete(Long surveyid);
	
	
	
}
