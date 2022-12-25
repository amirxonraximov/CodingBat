package com.example.lesson_1_task_2.repository;

import com.example.lesson_1_task_2.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

}
