package net.aydini.ml.surveyservice.service.factory.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.aydini.ml.surveyservice.domain.survey.Survey;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;


@Component
public class SurveyFactoryImpl implements SurveyFactory {

	@Override
	public Survey toDto(SurveyEntity entity) {
		if (entity == null)
			return null;
		return Survey.builder().title(entity.getTitle()).id(entity.getId())
				.description(entity.getDescription())
				.build();
	}


	@Override
	public SurveyEntity toEntity(Survey dto) {
		if (dto == null)
			return null;
		return SurveyEntity.builder().title(dto.getTitle()).id(dto.getId()).build();
	}

	@Override
	public List<Survey> toDtoList(List<SurveyEntity> entityList) {
		if (CollectionUtils.isEmpty(entityList))
			return new ArrayList<>();
		return entityList.stream().map(item -> toDto(item)).collect(Collectors.toList());
	}

	@Override
	public List<SurveyEntity> toEntityList(List<Survey> dtoList) {

		if (CollectionUtils.isEmpty(dtoList))
			return new ArrayList<>();
		return dtoList.stream().map(item -> toEntity(item)).collect(Collectors.toList());
	}

}
