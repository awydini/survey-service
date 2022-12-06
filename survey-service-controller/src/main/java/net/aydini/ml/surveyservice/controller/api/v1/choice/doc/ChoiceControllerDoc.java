package net.aydini.ml.surveyservice.controller.api.v1.choice.doc;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.aydini.ml.surveyservice.domain.choice.Choice;
import net.aydini.ml.surveyservice.domain.response.ResponseData;
import net.aydini.ml.surveyservice.domain.response.ResponseDataList;
import net.aydini.ml.surveyservice.domain.response.ResponseMessage;

public interface ChoiceControllerDoc {
	
	
	@Operation(summary = "Get Choice by id")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Choice could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseData<Choice>> findById(Long id);

	
	@Operation(summary = "Get All Choices.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Choice could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseDataList<Choice>> findAll(Optional<Integer> optionalPage,
			Optional<Integer> optionalSize);

	
	@Operation(summary = "Create a new Choice.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseMessage> create(Choice Choice);

	
//	@Operation(summary = "update  a Choice.")
//	@ApiResponse(responseCode = "200", description = "Successful operation.")
//	@ApiResponse(responseCode = "400", description = "invalid inputs.", content = @Content(schema = @Schema(hidden = true)))
//	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
//	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
//	public ResponseEntity<ResponseMessage> update(Choice survet);

	
	@Operation(summary = "Delete a Choice.")
	@ApiResponse(responseCode = "200", description = "Successful operation.")
	@ApiResponse(responseCode = "401", description = "User is not authorized.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404", description = "Choice could not be found.", content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(schema = @Schema(hidden = true)))
	public ResponseEntity<ResponseMessage> delete(Long id);

}
