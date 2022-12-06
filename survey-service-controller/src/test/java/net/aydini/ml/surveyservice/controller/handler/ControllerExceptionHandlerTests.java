package net.aydini.ml.surveyservice.controller.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.exception.ValidationException;

public class ControllerExceptionHandlerTests {

	
	@Test
	public void handleNotFoundErro_shouldReturn404()
	{
		var instance = new ControllerExceptionHandler();
		var response = instance.handleNotFoundError(new NotFoundException(""));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void handleValidationError_shouldReturn400()
	{
		var instance = new ControllerExceptionHandler();
		var response = instance.handleValidationError(new ValidationException(""));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void handleException_shouldReturn500()
	{
		var instance = new ControllerExceptionHandler();
		var response = instance.handleException(new Exception(""));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	
	@Test
	public void handleException_shouldReturn406()
	{
		var instance = new ControllerExceptionHandler();
		
		BindingResult bindingResult = new BindException(new Object(), "dummy object");
		var response = instance.handleMethodArgumentNotValid(new MethodArgumentNotValidException(null,bindingResult),null,null,null);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
	}
	
	
}
