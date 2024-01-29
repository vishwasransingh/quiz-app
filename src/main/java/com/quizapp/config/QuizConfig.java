package com.quizapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuizConfig {

	@Value("${quiz.file.path}")
    private String quizFilePath;
	
	public String getQuizFilePath() {
        return quizFilePath;
    }
	
}
