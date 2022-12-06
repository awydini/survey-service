package net.aydini.ml.surveyservice.service.factory.question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.repository.question.QuestionEntity;

@Component
public class QuestionFactoryImpl implements QuestionFactory {

	@Override
	public Question toDto(QuestionEntity entity) {
		return Question.builder()
				.description(entity.getDescription())
				.id(entity.getId())
				.title(entity.getTitle())
				.enabled(entity.isEnabled())
				.showDescription(entity.isShowDescription())
				.build();
	}

	@Override
	public QuestionEntity toEntity(Question question) {
		return QuestionEntity.builder()
				.description(question.getDescription())
				.title(question.getTitle())
				.id(question.getId())
				.enabled(question.isEnabled())
				.showDescription(question.isShowDescription())
				.build();
	}


	@Override
	public List<Question> toDtoList(List<QuestionEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
			return new ArrayList<>();
		return entityList.stream().map(item -> toDto(item)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionEntity> toEntityList(List<Question> dtoList) {
		
		if(CollectionUtils.isEmpty(dtoList))
			return new ArrayList<>();
		return dtoList.stream().map(item -> toEntity(item)).collect(Collectors.toList());
	}
	
	
	

}
