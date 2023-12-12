package com.moneymanager.moneymanager.controller.exceptions.handler;

import com.moneymanager.moneymanager.controller.exceptions.handler.body.ExceptionResponseBody;
import com.moneymanager.moneymanager.domain.exceptions.DomainException;
import com.moneymanager.moneymanager.repository.exceptions.EntityNotFoundException;
import com.moneymanager.moneymanager.repository.exceptions.PersistenceExcetion;
import com.moneymanager.moneymanager.service.exceptions.ServiceException;
import com.moneymanager.moneymanager.utils.InstantUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ActivityControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DomainException.class)
    protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(final DomainException exception, final HttpServletRequest request){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(PersistenceExcetion.class)
    protected ResponseEntity<ExceptionResponseBody> handlePersistenceExceptions(final PersistenceExcetion exception, final HttpServletRequest request){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ExceptionResponseBody> handleServiceExceptions(final ServiceException exception, final HttpServletRequest request){

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponseBody> handleExceptions(final Exception exception, final HttpServletRequest request) {

        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

        @ExceptionHandler(EntityNotFoundException.class)
        protected ResponseEntity<ExceptionResponseBody> handleEntityNotFoundExceptions(final EntityNotFoundException exception, final HttpServletRequest request){

            final var body = new ExceptionResponseBody(
                    InstantUtils.now(),
                    HttpStatus.NOT_FOUND.value(),
                    exception.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
