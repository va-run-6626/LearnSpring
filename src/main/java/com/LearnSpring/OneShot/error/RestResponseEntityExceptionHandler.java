package com.LearnSpring.OneShot.error; // Package declaration for error handling classes

import com.LearnSpring.OneShot.entity.ErrorMessage; // Importing the ErrorMessage class which holds details of the error
import org.springframework.http.HttpStatus; // Importing HttpStatus to return appropriate HTTP response codes
import org.springframework.http.ResponseEntity; // Importing ResponseEntity to wrap the response in an HTTP entity
import org.springframework.web.bind.annotation.ControllerAdvice; // Importing ControllerAdvice for global exception handling
import org.springframework.web.bind.annotation.ExceptionHandler; // Importing ExceptionHandler to handle specific exceptions
import org.springframework.web.bind.annotation.ResponseStatus; // Importing ResponseStatus for setting the response status
import org.springframework.web.context.request.WebRequest; // Importing WebRequest to access details about the web request
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler; // Extending ResponseEntityExceptionHandler for better exception handling support

/**
 * This class handles exceptions globally across the application.
 * It extends ResponseEntityExceptionHandler to provide customized exception handling.
 */
@ControllerAdvice // Marks this class as a global exception handler for all controllers
@ResponseStatus // Used to specify the HTTP status code for the response
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception handler for DepartmentNotFoundException.
     * This method catches the exception and returns an appropriate error message with HTTP 404 (Not Found).
     *
     * @param exception  The thrown DepartmentNotFoundException instance
     * @param webRequest The WebRequest object to access web request details
     * @return A ResponseEntity containing the error message and the NOT_FOUND HTTP status
     */
    @ExceptionHandler(DepartmentNotFoundException.class) // Specifies that this method handles DepartmentNotFoundException
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest webRequest) {
        // Creating an ErrorMessage object with the HTTP status and exception message
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        // Returning the ResponseEntity with the error message and setting the HTTP status to 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
