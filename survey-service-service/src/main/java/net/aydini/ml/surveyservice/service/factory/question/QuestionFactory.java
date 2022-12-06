package net.aydini.ml.surveyservice.service.factory.question;

import java.util.List;

import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.repository.question.QuestionEntity;

public interface QuestionFactory {
	
	
	Question toDto(QuestionEntity entity);
	
	
	QuestionEntity toEntity(Question choice);
	
	
	List<Question> toDtoList(List<QuestionEntity> entityList);
	
	
	List<QuestionEntity> toEntityList(List<Question> dtoList);
	

}
