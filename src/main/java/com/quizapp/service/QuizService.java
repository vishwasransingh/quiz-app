package com.quizapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizapp.config.QuizConfig;
import com.quizapp.model.Question;
import com.quizapp.model.QuizForm;
import com.quizapp.model.TestResult;

@Service
public class QuizService {
	
	private static String currentTestName;
	
	@Autowired
	QuizConfig quizConfig;
	
	public List<Question> getQuiz(String testName) {
		currentTestName = testName;
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            Resource resource = new ClassPathResource("files/" + testName + ".json");
            InputStream inputStream = resource.getInputStream();
            
            return objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});
        } catch (IOException e) {
            // Handle exceptions (cases : file not found, invalid JSON)
            e.printStackTrace();
            return Collections.emptyList();
        }
	}

	public TestResult calculateResult(QuizForm quizForm) {
		List<String> selectedAnswers = quizForm.getAnswers();
		List<String> correctAnswers = getAnswers();
		
		if (selectedAnswers == null) {
			return new TestResult();
		}
		
		int countCorrect = 0;
		int countAttempted = 0;
		
		Iterator<String> iterator1 = selectedAnswers.iterator();
		Iterator<String> iterator2 = correctAnswers.iterator();
		
		while( iterator1.hasNext() &&  iterator2.hasNext()){
			String selectedAnswer = iterator1.next();
			String correctAnswer = iterator2.next();
			if (selectedAnswer != null) {
				++ countAttempted;
				if (selectedAnswer.equals(correctAnswer))
					++ countCorrect;
			}
		}

		TestResult result = new TestResult();
		result.setAttemptedQuestions(countAttempted);
		result.setTotalQuestions(correctAnswers.size());
		result.setCorrectAnswers(countCorrect);
		result.setPercentageScore(countAttempted * 100 / correctAnswers.size());
		
		return result;
	}

	private List<String> getAnswers() {
		List<String> correctAnswers = new ArrayList<>();
		getQuiz(currentTestName).stream().forEach( question -> correctAnswers.add(question.getAnswer()));
		return correctAnswers;
	}
}
