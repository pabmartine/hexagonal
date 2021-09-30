package com.example.testjava.adapters.in.rest.errors;

import java.text.ParseException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Controller Error Handler
 * @author pmarti14
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Manage formatting errors
	 * @param ex the exception
	 * @param request the request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		StringBuilder msg = new StringBuilder("Formato de " + ex.getName() + " incorrecto");
		if (ex.getCause() instanceof ParseException)
			msg.append(" (dd/MM/yyyy HH:mm:ss)");
		else
			msg.append(" (numérico)");

		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), msg.toString(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Manage global errors
	 * @param ex the exception
	 * @param request the request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}