package com.example.lesson_1_task_2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String text;

    private String solution;

    private String hint;

    private String method;

    @ManyToOne
    private User user;

    @ManyToOne
    private Language language;

    private boolean hasStar;
}
