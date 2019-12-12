package ru.lanit.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.lanit.dto.ApiError;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex.getBindingResult().getAllErrors().size() > 0) {
            return handleExceptionInternal(ex, new ApiError(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), headers, HttpStatus.BAD_REQUEST, request);
        }
        return handleExceptionInternal(ex, new ApiError("Ошибка валидации"), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, new ApiError("Ошибка валидации"), headers, HttpStatus.BAD_REQUEST, request);
    }
}