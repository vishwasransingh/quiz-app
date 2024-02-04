package com.quizapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TestResult {
	private String userName;
	private int attemptedQuestions;
	private int totalQuestions;
	private int correctAnswers;
	private double percentageScore;
	private List<Question> feedbackQuestions;
}