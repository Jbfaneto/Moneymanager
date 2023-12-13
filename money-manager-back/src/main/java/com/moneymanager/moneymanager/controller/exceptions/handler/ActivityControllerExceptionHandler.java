package com.moneymanager.moneymanager.controller.exceptions.handler;

import com.moneymanager.moneymanager.controller.exceptions.handler.body.ExceptionResponseBody;
import com.moneymanager.moneymanager.domain.exceptions.DomainException;
import com.moneymanager.moneymanager.repository.exceptions.EntityNotFoundException;
import com.moneymanager.moneymanager.service.exceptions.ServiceException;
import com.moneymanager.moneymanager.utils.InstantUtils;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice("com.moneymanager.moneymanager.controller.activity")
public class ActivityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DomainException.class })
    protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(
            final DomainException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = { PersistenceException.class })
    protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(
            final PersistenceException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = { ServiceException.class })
    protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(
            final ServiceException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<ExceptionResponseBody> handleEntityNotFoundException(
            final EntityNotFoundException exception,
            final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);

    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(
            final Exception Exception,
            final HttpServletRequest Request) {

        final var Body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Exception.getMessage(),
                Request.getRequestURI());

        return ResponseEntity.internalServerError().body(Body);
    }

}