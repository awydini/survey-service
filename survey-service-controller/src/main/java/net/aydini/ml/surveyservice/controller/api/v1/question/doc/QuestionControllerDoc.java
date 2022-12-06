package net.aydini.ml.surveyservice.controller.api.v1.question.doc;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.domain.common.Status;
import net.aydini.ml.surveyservice.domain.question.Question;
import net.aydini.ml.surveyservice.domain.response.ResponseData;
import net.aydini.ml.surveyservice.domain.response.ResponseDataList;
import net.aydini.ml.surveyservice.domain.response.ResponseMessage;

public interface QuestionControllerDoc {
	
	
	@Operation(summary = "Get question by id")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseData<Question>> findById(Long id);

	
	@Operation(summary = "Get All questions.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseDataList<Question>> findAll(Optional<Integer> optionalPage,
			Optional<Integer> optionalSize);

	
	@Operation(summary = "Create new question.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	@RequestBody(content = { @Content( examples = {@ExampleObject(value = "{\"title\": \"example1\", \"description\":\"example1\", \"id\":0,\"showDescription\": true,  \"surveyId\":0}"),})})
	public ResponseEntity<ResponseMessage> create(Question question);

	
	@Operation(summary = "update question.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	@RequestBody(content = { @Content( examples = {@ExampleObject(value = "{\"title\": \"example1\", \"description\":\"example1\", \"id\":0,\"showDescription\": true,  \"surveyId\":0}"),})})
	public ResponseEntity<ResponseMessage> update(Question question);

	
	@Operation(summary = "Delete question.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseMessage> delete(Long id);
	
	
	
	@Operation(summary = "update question enabled status.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseMessage> updateEnabled(Long id, Status status);
	
	
	@Operation(summary = "Get All question choices.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "choice or question could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseDataList<Choice>> findQuestionChoicess( Long id,Optional<Integer> optionalPage,Optional<Integer> optionalSize);

}
