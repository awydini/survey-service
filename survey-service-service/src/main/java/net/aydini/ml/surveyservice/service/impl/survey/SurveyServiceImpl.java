package net.aydini.ml.surveyservice.service.impl.survey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.survey.Survey;
import net.aydini.ml.surveyservice.domain.survey.SurveyService;
import net.aydini.ml.surveyservice.domain.util.CustomPage;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;
import net.aydini.ml.surveyservice.repository.survey.SurveyRepository;
import net.aydini.ml.surveyservice.service.factory.survey.SurveyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
	
	
	private final SurveyRepository surveyRepository;
	
	
	private final SurveyFactory surveyFactory;

	@Override
	public Survey findById(Long surveyId) {
		
		return surveyRepository.findById(surveyId).map(surveyFactory::toDto).orElseThrow(() -> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + surveyId));
	}

	@Override
	public List<Survey> findAll() {
		Long count = surveyRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		return surveyRepository.findAllActive().stream().map(surveyFactory::toDto).collect(Collectors.toList());
	}

	@Override
	public CustomPage<Survey> findAll(Pageable pageable) {
		Long count = surveyRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		List<Survey> surveys = surveyRepository.findAllActive(pageable).getContent().stream()
				.map(surveyFactory::toDto).collect(Collectors.toList());
		return new CustomPage<Survey>(surveys, pageable, count);
	}

	@Transactional
	@Override
	public void create(Survey survey) {
		surveyRepository.save(surveyFactory.toEntity(survey));
		log.info("entity created : {}",survey);
	}

	@Transactional
	@Override
	public void update(Survey survey) {
		SurveyEntity surveyEntity = surveyRepository.findById(survey.getId()).orElseThrow(() -> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + survey.getId()));
		surveyEntity.setTitle(survey.getTitle());
		surveyEntity.setDescription(survey.getDescription());
		
		surveyRepository.save(surveyEntity);
		log.info("entity updated : {}",survey);
		
	}

	@Transactional
	@Override
	public void delete(Long surveyid) {
		surveyRepository.deleteSoft(surveyid, LocalDateTime.now());
		log.info("entity with id {} has been deleted.",surveyid);
	}



}
