package net.aydini.ml.surveyservice.controller.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;
import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.exception.ValidationException;
import net.aydini.ml.surveyservice.domain.response.ResponseError;




@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

	
	
	 @ResponseBody
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<ResponseError> handleNotFoundError(NotFoundException ex)
	    {
	        log.error("not found error!. {} ", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),ex.getMessage()));
	    }

	    @ResponseBody
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(ValidationException.class)
	    public ResponseEntity<ResponseError> handleValidationError(ValidationException ex)
	    {
	        log.error("validation error!. {}", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
	    }
	    
	    @Override
	    @ResponseBody
	    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(item->item.getDefaultMessage()).collect(Collectors.toList());
	        log.error("validation error!. {}", errorList);
	        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseError(HttpStatus.NOT_ACCEPTABLE.name(), HttpStatus.NOT_ACCEPTABLE.value(),errorList));
	    }
	    
	    
	    @ResponseBody
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ResponseError> handleException(Exception ex)
	    {
	        log.error("error : {}", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage()));
	    }
	    
	    
	    
	    @Override
	    protected ResponseEntity<Object> handleExceptionInternal(
	            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

	        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
	            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
	        }
	        return  ResponseEntity.status(status).body(new ResponseError(ex.getMessage(), status.value(),ex.getMessage()));
	    }
}
