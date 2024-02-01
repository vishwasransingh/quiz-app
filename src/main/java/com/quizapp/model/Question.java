package com.quizapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Question {
	private int questionId;
    private String question;
    private List<String> options;
    @JsonProperty("answer")
    private String correctAnswer;
    private String userSelectedAnswer;
    private String difficulty;
}
