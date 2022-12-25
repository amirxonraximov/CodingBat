package com.example.lesson_1_task_2.payload;

import com.example.lesson_1_task_2.entity.Task;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ExampleDto {

    private String text;

    private Integer taskId;
}
