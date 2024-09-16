package lk.purna.healthy_life.exception;

import lk.purna.healthy_life.controller.response.error.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdviser {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public CustomErrorResponse handleNFException(Exception exception) {

        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(exception.getMessage());

        return customErrorResponse;

    }

    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(DateException.class)
    public CustomErrorResponse duplicateDateException(Exception exception) {

        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(exception.getMessage());

        return customErrorResponse;

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidCredentialsException.class)
    public CustomErrorResponse handleRunTimeException(RuntimeException exception) {

        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(exception.getMessage());

        return customErrorResponse;

    }
}
