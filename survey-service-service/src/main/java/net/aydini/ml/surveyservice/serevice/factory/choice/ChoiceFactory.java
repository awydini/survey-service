package net.aydini.ml.surveyservice.serevice.factory.choice;

import java.util.List;

import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.repository.choice.ChoiceEntity;

public interface ChoiceFactory {
	
	
	Choice toDto(ChoiceEntity entity);
	
	
	ChoiceEntity toEntity(Choice choice);
	
	
	List<Choice> toDtoList(List<ChoiceEntity> entityList);
	
	
	List<ChoiceEntity> toEntityList(List<Choice> dtoList);
	
	

}
