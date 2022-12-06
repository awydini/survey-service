package net.aydini.ml.surveyservice.service.impl.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.domain.question.QuestionService;
import net.aydini.ml.surveyservice.domain.util.CustomPage;
import net.aydini.ml.surveyservice.repository.question.QuestionEntity;
import net.aydini.ml.surveyservice.repository.question.QuestionRepository;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;
import net.aydini.ml.surveyservice.repository.survey.SurveyRepository;
import net.aydini.ml.surveyservice.service.factory.question.QuestionFactory;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImple implements QuestionService {

	
	private final QuestionRepository questionRepository;
	
	private final SurveyRepository surveyRepository;
	
	
	private final QuestionFactory questionFactory;
	


	@Override
	public Question findById(Long questionId) {
		return questionRepository.findById(questionId).map(item ->questionFactory.toDto(item)).orElseThrow(()-> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + questionId));
	}

	@Override
	public List<Question> findAll() {
		Long count = questionRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		return questionRepository.findAllActive().stream().map(questionFactory::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<Question> findAll(Pageable page) {
		Long count = questionRepository.countActive();
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		List<Question> surveys = questionRepository.findAllActive(page).getContent().stream()
				.map(questionFactory::toDto).collect(Collectors.toList());
		return new CustomPage<Question>(surveys, page, count);
	}

	@Transactional
	@Override
	public void create(Question question) {
		
		SurveyEntity surveyEntity = surveyRepository.findById(question.getSurveyId()).orElseThrow(()->new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + question.getSurveyId()));
		QuestionEntity entity = questionFactory.toEntity(question);
		entity.setSurvey(surveyEntity);
		entity.setEnabled(true);
		questionRepository.save(entity);
		
		log.info("entity created : {}",question);
		
	}

	@Transactional
	@Override
	public void update(Question question) {
		QuestionEntity entity = questionRepository.findById(question.getId()).orElseThrow(()-> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + question.getId()));
		entity.setTitle(question.getTitle());
		entity.setDescription(question.getDescription());
		entity.setShowDescription(false);
		questionRepository.save(entity);
		
		log.info("entity updated : {}",question);
		
	}

	@Transactional
	@Override
	public void delete(Long questionId) {
		questionRepository.deleteSoft(questionId, LocalDateTime.now());
		log.info("entity with id {} has been deleted.",questionId);
		
	}

	@Transactional
	@Override
	public void updateEnabled(Long id, Boolean enabled) {
		QuestionEntity questionEntity = questionRepository.findById(id).orElseThrow(()-> new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + id));
		questionEntity.setEnabled(enabled);
		questionRepository.save(questionEntity);
		
		log.info("entity updated with new status : {}",questionEntity);
		
	}

	@Override
	public CustomPage<Question> findSurveyQuestions(Long surveyId, Pageable page) {
		Long count = questionRepository.countSurveyQuestions(surveyId);
		if (count == null || count.intValue() == 0)
			throw new NotFoundException(NotFoundException.NOT_FOUND);
		List<Question> questions = questionRepository.findSurveyQuestions(surveyId,page).getContent().stream()
				.map(questionFactory::toDto).collect(Collectors.toList());
		return new CustomPage<Question>(questions, page, count);
	}

}
