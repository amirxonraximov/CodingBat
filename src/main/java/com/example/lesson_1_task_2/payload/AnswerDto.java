package com.example.lesson_1_task_2.payload;

import lombok.Data;

@Data
public class AnswerDto {

    private String text;
    private Integer taskId;
    private Integer userId;

}
