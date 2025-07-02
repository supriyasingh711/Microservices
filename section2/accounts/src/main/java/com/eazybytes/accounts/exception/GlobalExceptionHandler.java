package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

/**
 * Handles MethodArgumentNotValidException exceptions and returns a response entity with validation error messages.
 *
 * @param ex the MethodArgumentNotValidException containing validation errors
 * @param headers the HTTP headers
 * @param status the HTTP status code
 * @param request the web request
 * @return a ResponseEntity containing a map of field names to validation messages and a BAD_REQUEST status
 */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
    /**
     * Handles CustomerAlreadyExistsException exceptions and returns a response entity with a BAD_REQUEST status
     * and a ErrorResponseDto containing the api path, BAD_REQUEST status, the exception message and the current time
     *
     * @param ex the CustomerAlreadyExistsException
     * @param request the web request
     * @return a ResponseEntity containing a ErrorResponseDto with the mentioned information and a BAD_REQUEST status
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),//only get the api path
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }
    /**
     * Handles ResourceNotFoundException exceptions and returns a response entity with a NOT_FOUND status
     * and an ErrorResponseDto containing the api path, NOT_FOUND status, the exception message, and the current time.
     *
     * @param ex the ResourceNotFoundException
     * @param request the web request
     * @return a ResponseEntity containing an ErrorResponseDto with the mentioned information and a NOT_FOUND status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),//only get the api path
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
    /**
     * Handles all uncaught exceptions and returns a response entity with an INTERNAL_SERVER_ERROR status
     * and an ErrorResponseDto containing the api path, INTERNAL_SERVER_ERROR status, the exception message,
     * and the current time.
     *
     * @param ex the exception that was thrown
     * @param request the web request
     * @return a ResponseEntity containing an ErrorResponseDto with the mentioned information and an
     * INTERNAL_SERVER_ERROR status
     */
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);

    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Handles all uncaught exceptions and returns a response entity with an INTERNAL_SERVER_ERROR status
     * and an ErrorResponseDto containing the api path, INTERNAL_SERVER_ERROR status, the exception message,
     * and the current time.
     *
     * @param ex the exception that was thrown
     * @param request the web request
     * @return a ResponseEntity containing an ErrorResponseDto with the mentioned information and an
     * INTERNAL_SERVER_ERROR status
     */

/* <<<<<<<<<<  2f8183d2-7ba7-416c-8519-9cdcb8d149cd  >>>>>>>>>>> */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),//only get the api path
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
