package com.moneymanager.moneymanager.controller.exceptions.handler;

import com.moneymanager.moneymanager.controller.exceptions.handler.body.ExceptionResponseBody;
import com.moneymanager.moneymanager.service.exceptions.AuthenticationException;
import com.moneymanager.moneymanager.service.exceptions.LoginException;
import com.moneymanager.moneymanager.utils.InstantUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.moneymanager.moneymanager.controller.authentication")
public class AuthControllerExceptionHandler {
    @ExceptionHandler(value = { LoginException.class })
    protected ResponseEntity<ExceptionResponseBody> handleLoginExceptions(
            final LoginException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(value = { AuthenticationException.class })
    protected ResponseEntity<ExceptionResponseBody> handleAuthExceptions(
            final AuthenticationException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ExceptionResponseBody> handleMethodArgumentNotValidExceptions(
            final MethodArgumentNotValidException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ExceptionResponseBody> handleExceptionExceptions(
            final Exception exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }
}
