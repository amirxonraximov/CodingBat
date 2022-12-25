package com.example.lesson_1_task_2.repository;

import com.example.lesson_1_task_2.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Integer> {

}
