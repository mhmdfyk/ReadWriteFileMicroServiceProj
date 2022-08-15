package com.INetwork.readwritefile.exceptions;


import com.INetwork.readwritefile.dto.ExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ExceptionResponseDto> handleException(Exception ex, WebRequest request) {
        if (ex instanceof BindException  || ex instanceof ConstraintViolationException) {
            String customErrorMsg = "bad data!";
            if (ex instanceof MethodArgumentNotValidException) {
                List<Map<String, String>> errorList = new ArrayList<>();
                for (ObjectError error : ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(((FieldError) error).getField(), error.getDefaultMessage());
                    errorList.add(errorMap);
                }
                return ResponseEntity.badRequest().body(new ExceptionResponseDto(customErrorMsg, errorList));
            }
            if (ex instanceof BindException) {
                List<Map<String, String>> errorList = new ArrayList<>();
                for (ObjectError error : ((BindException) ex).getBindingResult().getAllErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(((FieldError) error).getField(), error.getDefaultMessage());
                    errorList.add(errorMap);
                }
                return ResponseEntity.badRequest().body(new ExceptionResponseDto(customErrorMsg, errorList));
            }

        }

        return ResponseEntity.badRequest().body(new ExceptionResponseDto(ex.getMessage(), new ArrayList<>()));
    }
}
