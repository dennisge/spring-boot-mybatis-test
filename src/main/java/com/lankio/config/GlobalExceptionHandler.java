package com.lankio.config;

import com.lankio.domain.common.Result;
import com.lankio.domain.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Result<String>> handleNotFound(ServletException e) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result<>(ResultCode.NOT_FOUND), headers, HttpStatus.OK);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<Result<Map<String, String>>> handleBindException(BindException e) {

        final BindingResult bindingResult = e.getBindingResult();

        return getBindingResult(bindingResult);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Result<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        final BindingResult bindingResult = e.getBindingResult();
        return getBindingResult(bindingResult);
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<Result<String>> handleTypeMismatchException(TypeMismatchException e) {

        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException error = (MethodArgumentTypeMismatchException) e;
            log.error("TypeMismatch: {}-{}", error.getName(), error.getRequiredType().getSimpleName());
        } else {
            log.error("TypeMismatch", e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
    }

    @ExceptionHandler({HttpMessageConversionException.class})
    public ResponseEntity<Result<String>> handleHttpMessageConversionException(HttpMessageConversionException e) {

        log.error("Param error: {}", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<Result<String>> handleDuplicateKeyException(DuplicateKeyException e) {

        log.error("DuplicateKey: {}", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result<>(ResultCode.CONFLICT_DUPLICATE_KEY), headers, HttpStatus.OK);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Result<String>> handleAllOtherException(Throwable e) {

        log.error(e.getMessage(), e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);

    }

    private ResponseEntity<Result<Map<String, String>>> getBindingResult(BindingResult bindingResult) {
        Map<String, String> errorTable = bindingResult.getAllErrors().stream().filter(o -> o instanceof FieldError).collect(Collectors.toMap(o ->
                        ((FieldError) o).getField()
                , ObjectError::getDefaultMessage));
        log.error("ParamError: {}", errorTable);

        Result<Map<String, String>> result = new Result<>(ResultCode.PARAM_ERROR.getCode(), ResultCode.PARAM_ERROR.getMsg(), errorTable);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

}