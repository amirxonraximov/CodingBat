package com.example.lesson_1_task_2.payload;

import com.example.lesson_1_task_2.entity.User;
import lombok.Data;

@Data
public class TaskDto {

    private String name;
    private String text;
    private String solution;
    private String hint;
    private String method;
    private Integer userId;
    private Integer languageId;
}
