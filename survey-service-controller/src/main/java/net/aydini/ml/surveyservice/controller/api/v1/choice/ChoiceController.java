package net.aydini.ml.surveyservice.controller.api.v1.choice;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import net.aydini.ml.surveyservice.controller.api.v1.choice.doc.ChoiceControllerDoc;
import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.domain.choice.ChoiceService;
import net.aydini.ml.surveyservice.domain.response.ResponseData;
import net.aydini.ml.surveyservice.domain.response.ResponseDataList;
import net.aydini.ml.surveyservice.domain.response.ResponseMessage;
import net.aydini.ml.surveyservice.domain.util.CommonConstant;


@RestController
@RequestMapping("/api/v1/choices")
@RequiredArgsConstructor
public class ChoiceController implements ChoiceControllerDoc{
	
	private final ChoiceService choiceService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData<Choice>> findById(@PathVariable(name = "id", required = true) Long id) {
		Choice choice = choiceService.findById(id);
		return ResponseEntity.ok().body(new ResponseData<>(HttpStatus.OK.name(), HttpStatus.OK.value(), choice));
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDataList<Choice>> findAll(
			@RequestParam(name = "page", required = false) Optional<Integer> optionalPage,
			@RequestParam(name = "size", required = false) Optional<Integer> optionalSize) {
		PageRequest pageRequest = PageRequest.of(optionalPage.orElse(0),
				optionalSize.orElse(CommonConstant.DEFAULT_PAGE_SIZ));
		Page<Choice> all = choiceService.findAll(pageRequest);

		return ResponseEntity.ok().body(new ResponseDataList<>(HttpStatus.OK.name(), HttpStatus.OK.value(), all));
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody Choice choice) {
		
		choiceService.create(choice);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value()),
				HttpStatus.OK);
		
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> delete(@PathVariable(name = "id", required = true) Long id) {
		choiceService.delete(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value()),
				HttpStatus.OK);
	}

}
