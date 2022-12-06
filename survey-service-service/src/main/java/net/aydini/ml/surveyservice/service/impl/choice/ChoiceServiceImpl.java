package net.aydini.ml.surveyservice.service.impl.choice;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.domain.choice.ChoiceService;
import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.util.CustomPage;
import net.aydini.ml.surveyservice.repository.choice.ChoiceEntity;
import net.aydini.ml.surveyservice.repository.choice.ChoiceRepository;
import net.aydini.ml.surveyservice.repository.question.QuestionEntity;
import net.aydini.ml.surveyservice.repository.question.QuestionRepository;
import net.aydini.ml.surveyservice.serevice.factory.choice.ChoiceFactory;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {
	
	private final ChoiceRepository choiceRepository;
	
	private final QuestionRepository questionRepository;
	
	private final ChoiceFactory choiceFactory;
	
	
	@Override
	public Choice findById(Long ChoiceId) {
		return choiceRepository.findById(ChoiceId).map(item ->choiceFactory.toDto(item)).orElseThrow(()-> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + ChoiceId));
	}

	@Override
	public List<Choice> findAll() {
		Long count = choiceRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		return choiceRepository.findAllActive().stream().map(choiceFactory::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<Choice> findAll(Pageable page) {
		Long count = choiceRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		List<Choice> surveys = choiceRepository.findAllActive(page).getContent().stream()
				.map(choiceFactory::toDto).collect(Collectors.toList());
		return new CustomPage<Choice>(surveys, page, count);
	}

	@Transactional
	@Override
	public void create(Choice choice) {
		
		QuestionEntity questionEntity = questionRepository.findById(choice.getQuestionId()).orElseThrow(()-> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + choice.getId()));
		ChoiceEntity entity = choiceFactory.toEntity(choice);
		entity.setQuestion(questionEntity);
		choiceRepository.save(entity);
		
		log.info("entity created : {}",choice);
		
	}


	@Transactional
	@Override
	public void delete(Long ChoiceId) {
		choiceRepository.deleteSoft(ChoiceId, LocalDateTime.now());
		log.info("entity with id {} has been deleted.",ChoiceId);
		
	}

	@Override
	public Page<Choice> findAllChoices(Long questionId, Pageable page) {
		Long count = choiceRepository.countQuestionChoicess(questionId);
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		List<Choice> choices = choiceRepository.findQuestionChoicess(questionId,page).getContent().stream()
				.map(choiceFactory::toDto).collect(Collectors.toList());
		return new CustomPage<Choice>(choices, page, count);
	}

}
