package net.aydini.ml.surveyservice.service.factory.survey;

import java.util.List;

import net.aydini.ml.surveyservice.domain.survey.Survey;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;

public interface SurveyFactory {
	
	
	Survey toDto(SurveyEntity entity);
	
	
	SurveyEntity toEntity(Survey choice);
	
	
	List<Survey> toDtoList(List<SurveyEntity> entityList);
	
	
	List<SurveyEntity> toEntityList(List<Survey> dtoList);

}
