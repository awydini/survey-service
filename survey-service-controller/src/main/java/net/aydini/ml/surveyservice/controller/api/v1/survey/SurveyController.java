package net.aydini.ml.surveyservice.controller.api.v1.survey;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.aydini.ml.surveyservice.controller.api.v1.survey.doc.SurveyControllerDoc;
import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.domain.question.QuestionService;
import net.aydini.ml.surveyservice.domain.response.ResponseData;
import net.aydini.ml.surveyservice.domain.response.ResponseDataList;
import net.aydini.ml.surveyservice.domain.response.ResponseMessage;
import net.aydini.ml.surveyservice.domain.survey.Survey;
import net.aydini.ml.surveyservice.domain.survey.SurveyService;
import net.aydini.ml.surveyservice.domain.util.CommonConstant;

@RestController
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
public class SurveyController implements SurveyControllerDoc {

	private final SurveyService surveyService;
	
	private final QuestionService questionService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData<Survey>> findById(@PathVariable(name = "id", required = true) Long id) {
		Survey survey = surveyService.findById(id);
		return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.OK.name(), HttpStatus.OK.value(), survey));
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDataList<Survey>> findAll(
			@RequestParam(name = "page", required = false) Optional<Integer> optionalPage,
			@RequestParam(name = "size", required = false) Optional<Integer> optionalSize) {
		PageRequest pageRequest = PageRequest.of(optionalPage.orElse(0),
				optionalSize.orElse(CommonConstant.DEFAULT_PAGE_SIZ));
		Page<Survey> all = surveyService.findAll(pageRequest);

		return ResponseEntity.ok().body(new ResponseDataList<>(HttpStatus.OK.name(), HttpStatus.OK.value(), all));
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody Survey survey) {
		surveyService.create(survey);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> update(@Valid @RequestBody  Survey survey) {
		
		surveyService.update(survey);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> delete(@PathVariable(name = "id", required = true) Long id) {
		surveyService.delete(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDataList<Question>> findSurveyQuestions(@PathVariable(name = "id", required = true) Long id,
			@RequestParam(name = "page", required = false) Optional<Integer> optionalPage,
			@RequestParam(name = "size", required = false) Optional<Integer> optionalSize
			) {
		PageRequest pageRequest = PageRequest.of(optionalPage.orElse(0),
				optionalSize.orElse(CommonConstant.DEFAULT_PAGE_SIZ));
		Page<Question> all = questionService.findSurveyQuestions(id,pageRequest);
		return ResponseEntity.ok().body(new ResponseDataList<>(HttpStatus.OK.name(), HttpStatus.OK.value(), all));
	}

}
