package com.vinayak.restfulservice.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vinayak.restfulservice.user.UserResource;

@ControllerAdvice(basePackageClasses = UserResource.class)
@RestController
public class UserResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError error : allErrors) {
			builder.append(error.getDefaultMessage());
			builder.append(",");
		}
		String errorString = builder.toString();
		ExceptionResponse response = new ExceptionResponse(new Date(), errorString, request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
