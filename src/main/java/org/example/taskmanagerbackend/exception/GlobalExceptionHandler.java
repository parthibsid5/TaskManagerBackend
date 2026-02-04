// Goal to create a global file for the executions to hit
package org.example.taskmanagerbackend.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{
    // for validation related errors

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValErrors (
            MethodArgumentNotValidException ex
            ){
        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    //for other errors
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRunerrors(
            RuntimeException ex
    ){
        Map<String,String> errors=new HashMap<>();
        errors.put("Error :",ex.getMessage());

        return ResponseEntity.status(404).body(errors);
    }
}