package com.example.lesson_1_task_2.service;

import com.example.lesson_1_task_2.entity.Example;
import com.example.lesson_1_task_2.entity.Task;
import com.example.lesson_1_task_2.payload.ApiResponse;
import com.example.lesson_1_task_2.payload.ExampleDto;
import com.example.lesson_1_task_2.repository.ExampleRepository;
import com.example.lesson_1_task_2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addExample(ExampleDto exampleDto) {

        Example example = new Example();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example added", true);
    }

    public List<Example> getExamples() {

        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }

    public Example getExampleById(Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse editExample(Integer id, ExampleDto exampleDto) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }

        Example editingExample = new Example();
        editingExample.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        editingExample.setTask(optionalTask.get());
        exampleRepository.save(editingExample);
        return new ApiResponse("Example edited", true);

    }

    public ApiResponse deleteExample(Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }
        exampleRepository.deleteById(id);
        return new ApiResponse("Example deleted", true);
    }
}
