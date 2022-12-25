package com.example.lesson_1_task_2.controller;

import com.example.lesson_1_task_2.entity.Language;
import com.example.lesson_1_task_2.payload.ApiResponse;
import com.example.lesson_1_task_2.payload.LanguageDto;
import com.example.lesson_1_task_2.service.LanguageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;


    @PostMapping
    public HttpEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto) {

        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {

        List<Language> languages = languageService.getLanguages();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/{id}")
    public HttpEntity<Language> getAddress(@PathVariable Integer id) {

        Language language = languageService.getlanguageById(id);
        return ResponseEntity.ok(language);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @RequestBody LanguageDto languageDto) {

        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id) {

        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
