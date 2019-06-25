package com.lankio.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

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
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.lankio.domain.common.Result;
import com.lankio.domain.common.ResultCode;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<Result<String>> handleNotFound(ServletException e) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.NOT_FOUND), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ BindException.class })
	public ResponseEntity<Result<String>> handleBindException(BindException e) {

		List<String> errorList = e.getBindingResult().getFieldErrors().stream().map(msg -> {
			return msg.getObjectName() + "." + msg.getField();
		}).collect(Collectors.toList());
		log.error("参数错误: {}", errorList);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Result<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		List<String> errorList = e.getBindingResult().getFieldErrors().stream().map(msg -> {
			return msg.getField() + ":" + msg.getDefaultMessage();
		}).collect(Collectors.toList());
		log.error("参数错误: {}", errorList);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ TypeMismatchException.class })
	public ResponseEntity<Result<String>> handleTypeMismatchException(TypeMismatchException e) {

		if (e instanceof MethodArgumentTypeMismatchException) {
			MethodArgumentTypeMismatchException error = (MethodArgumentTypeMismatchException) e;
			log.error("参数类型错误: {}-{}", error.getName(), error.getRequiredType().getSimpleName());
		} else {
			log.error("参数类型错误", e);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ HttpMessageConversionException.class })
	public ResponseEntity<Result<String>> handleHttpMessageConversionException(HttpMessageConversionException e) {

		log.error("参数错误: {}", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ DuplicateKeyException.class })
	public ResponseEntity<Result<String>> handleDuplicateKeyException(DuplicateKeyException e) {

		log.error("唯一约束冲突: {}", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.CONFLICT_DUPLICATE_KEY), headers, HttpStatus.OK);
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Result<String>> handleAllOtherException(Throwable e) {

		log.error(e.getMessage(), e);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(new Result<>(ResultCode.BAD_REQUEST), headers, HttpStatus.OK);

	}

}