package net.aydini.ml.surveyservice.serevice.factory.choice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.repository.choice.ChoiceEntity;

@Component
public class ChoiceFactoryImpl implements ChoiceFactory {

	@Override
	public Choice toDto(ChoiceEntity entity) {
		if (entity == null)
			return null;
		return Choice.builder().title(entity.getTitle()).id(entity.getId()).build();
	}

	@Override
	public ChoiceEntity toEntity(Choice dto) {
		if (dto == null)
			return null;

		return ChoiceEntity.builder().title(dto.getTitle()).id(dto.getId()).build();
	}

	@Override
	public List<Choice> toDtoList(List<ChoiceEntity> entityList) {
		if (CollectionUtils.isEmpty(entityList))
			return new ArrayList<>();
		return entityList.stream().map(item -> toDto(item)).collect(Collectors.toList());
	}

	@Override
	public List<ChoiceEntity> toEntityList(List<Choice> dtoList) {

		if (CollectionUtils.isEmpty(dtoList))
			return new ArrayList<>();
		return dtoList.stream().map(item -> toEntity(item)).collect(Collectors.toList());
	}

}
