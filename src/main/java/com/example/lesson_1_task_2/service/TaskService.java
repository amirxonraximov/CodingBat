package com.example.lesson_1_task_2.service;

import com.example.lesson_1_task_2.entity.Language;
import com.example.lesson_1_task_2.entity.Task;
import com.example.lesson_1_task_2.entity.User;
import com.example.lesson_1_task_2.payload.ApiResponse;
import com.example.lesson_1_task_2.payload.TaskDto;
import com.example.lesson_1_task_2.repository.LanguageRepository;
import com.example.lesson_1_task_2.repository.TaskRepository;
import com.example.lesson_1_task_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addTask(TaskDto taskDto) {

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setSolution(taskDto.getSolution());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());

        Optional<User> optionalUser = userRepository.findById(taskDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        task.setUser(optionalUser.get());

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        task.setLanguage(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Task added", true);
    }

    public List<Task> getTasks() {

        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    public Task getTaskById(Integer id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }

        Task editingTask = new Task();
        editingTask.setName(taskDto.getName());
        editingTask.setText(taskDto.getText());
        editingTask.setSolution(taskDto.getSolution());
        editingTask.setHint(taskDto.getHint());
        editingTask.setMethod(taskDto.getMethod());

        Optional<User> optionalUser = userRepository.findById(taskDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        editingTask.setUser(optionalUser.get());

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Language not found", false);
        }
        editingTask.setLanguage(optionalLanguage.get());
        taskRepository.save(editingTask);
        return new ApiResponse("Task edited", true);
    }

    public ApiResponse deleteTask(Integer id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        taskRepository.deleteById(id);
        return new ApiResponse("Task deleted", true);
    }
}
