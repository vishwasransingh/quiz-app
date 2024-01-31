package com.quizapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class QuizConfig {

	@Value("${quiz.file.path}")
	private String quizFilePath;

	@Value("${quiz.file.path}")
	private String quizFileExtension;

}
