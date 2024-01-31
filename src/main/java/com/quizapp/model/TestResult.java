package com.quizapp.model;

import lombok.Data;

@Data
public class TestResult {
	
	private int attemptedQuestions;
	private int totalQuestions;
	private int correctAnswers;
	private double percentageScore;
	
}
