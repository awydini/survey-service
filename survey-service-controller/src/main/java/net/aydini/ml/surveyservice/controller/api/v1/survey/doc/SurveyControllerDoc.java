package net.aydini.ml.surveyservice.controller.api.v1.survey.doc;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.domain.response.ResponseData;
import net.aydini.ml.surveyservice.domain.response.ResponseDataList;
import net.aydini.ml.surveyservice.domain.response.ResponseMessage;
import net.aydini.ml.surveyservice.domain.survey.Survey;

public interface SurveyControllerDoc {
	
	
	@Operation(summary = "Get Survey by id")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Survey could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseData<Survey>> findById(Long id);

	
	@Operation(summary = "Get All surveys.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Survey could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseDataList<Survey>> findAll(Optional<Integer> optionalPage,
			Optional<Integer> optionalSize);

	
	@Operation(summary = "Create a new Survey.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	@RequestBody(content = { @Content( examples = {@ExampleObject(value = "{\"title\": \"example1\", \"description\":\"example1\", \"id\":0}"),})})
	public ResponseEntity<ResponseMessage> create(Survey survey);

	
	@Operation(summary = "update  a Survey.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	@RequestBody(content = {
	        @Content( examples = {
	            @ExampleObject(value = "{\"title\": \"example1\", \"description\":\"example1\", \"id\":0}"),
	            })
	    })
	public ResponseEntity<ResponseMessage> update(Survey survet);

	
	@Operation(summary = "Delete a Survey.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Survey could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseMessage> delete(Long id);
	
	
	@Operation(summary = "Get All survey questions.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Survey or question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseDataList<Question>> findSurveyQuestions( Long id,Optional<Integer> optionalPage,Optional<Integer> optionalSize);

}
