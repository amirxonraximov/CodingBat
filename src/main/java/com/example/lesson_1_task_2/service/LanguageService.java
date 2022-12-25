package com.example.lesson_1_task_2.service;

import com.example.lesson_1_task_2.entity.Language;
import com.example.lesson_1_task_2.payload.ApiResponse;
import com.example.lesson_1_task_2.payload.LanguageDto;
import com.example.lesson_1_task_2.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addLanguage(LanguageDto languageDto) {

        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language added", true);
    }

    public List<Language> getLanguages() {

        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }

    public Language getlanguageById(Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        Language editingLanguage = optionalLanguage.get();
        editingLanguage.setName(languageDto.getName());
        languageRepository.save(editingLanguage);
        return new ApiResponse("Language edited", true);
    }

    public ApiResponse deleteLanguage(Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        languageRepository.deleteById(id);
        return new ApiResponse("Language deleted", true);
    }
}
