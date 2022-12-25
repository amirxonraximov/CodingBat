package com.example.lesson_1_task_2.repository;

import com.example.lesson_1_task_2.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
