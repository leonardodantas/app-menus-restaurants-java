package com.br.rank.list.infra.http.controllers.advice;

import com.br.rank.list.app.exceptions.CodeNotFoundException;
import com.br.rank.list.app.exceptions.PromotionAlreadyExistException;
import com.br.rank.list.app.exceptions.RestaurantNotFoundException;
import com.br.rank.list.domains.exceptions.TimeBetweenHoursException;
import com.br.rank.list.infra.http.controllers.advice.responses.ErrorResponse;
import com.br.rank.list.infra.http.controllers.advice.responses.ErrorsResponse;
import com.br.rank.list.infra.http.controllers.advice.responses.MessageError;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionAdvice {

    private final MessageSource messageSource;

    public AppExceptionAdvice(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> handleRestaurantNotFoundException(final RestaurantNotFoundException exception) {
        final var response = ErrorResponse.from(exception);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CodeNotFoundException.class)
    public ResponseEntity<?> handleCodeNotFoundException(final CodeNotFoundException exception) {
        final var response = ErrorResponse.from(exception);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PromotionAlreadyExistException.class)
    public ResponseEntity<?> handlePromotionAlreadyExistException(final PromotionAlreadyExistException exception) {
        final var response = ErrorResponse.from(exception);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeBetweenHoursException.class)
    public ResponseEntity<?> handleTimeBetweenHoursException(final TimeBetweenHoursException exception) {
        final var response = ErrorResponse.from(exception);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final var fields = exception.getBindingResult().getFieldErrors();

        final var errors = fields.stream()
                .map(field ->
                        MessageError.of(field, messageSource.getMessage(field, LocaleContextHolder.getLocale()))
                )
                .toList();

        final var response = ErrorsResponse.from(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
