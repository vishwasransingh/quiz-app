package com.quizapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	private static String currentQuizName;
	private static List<Question> currentQuizList;
	private static String currentQuizDifficulty;
	
	@Autowired
	QuizConfig quizConfig;

	public List<Question> getQuiz(String quizName, String quizDifficulty, int numberOfQuestions) {
		
		currentQuizName = quizName;
	    currentQuizDifficulty = quizDifficulty;

	    try {
	        ObjectMapper objectMapper = new ObjectMapper();

	        Resource resource = new ClassPathResource(quizConfig.getQuizFilePath() + quizName + ".json");
	        InputStream inputStream = resource.getInputStream();

	        List<Question> allQuestions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});

	        List<Question> filteredQuestions = allQuestions.stream()
	                .filter(question -> question.getDifficulty().equals(quizDifficulty))
	                .collect(Collectors.toList());

	        // Shuffle the list of questions before limiting to the user-defined number
	        Collections.shuffle(filteredQuestions);

	        // Limit the questions to the user-defined number
	        filteredQuestions = filteredQuestions.stream()
	                .limit(numberOfQuestions)
	                .collect(Collectors.toList());

	        currentQuizList = filteredQuestions;

	        return filteredQuestions;
	    } catch (IOException e) {
	        // Handle exceptions (cases: file not found, invalid JSON)
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}


	public TestResult calculateResult(QuizForm quizForm) {
		List<String> selectedAnswers = quizForm.getAnswers();
		List<String> correctAnswers = getAnswersForCurrentQuiz();
		
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
		result.setPercentageScore(countCorrect * 100 / correctAnswers.size());
		return result;
	}

	private List<String> getAnswersForCurrentQuiz() {
		List<String> correctAnswers = new ArrayList<>();		
		currentQuizList.stream().forEach(question -> correctAnswers.add(question.getAnswer()));
		return correctAnswers;
	}
}
