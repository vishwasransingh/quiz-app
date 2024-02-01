package com.quizapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
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


//	public TestResult calculateResult(QuizForm quizForm) {
//		List<String> selectedAnswers = quizForm.getAnswers();
//		List<String> correctAnswers = getAnswersForCurrentQuiz();
//		
//		if (selectedAnswers == null) {
//			return new TestResult();
//		}
//		
//		int countCorrect = 0;
//		int countAttempted = 0;
//		
//		Iterator<String> iterator1 = selectedAnswers.iterator();
//		Iterator<String> iterator2 = correctAnswers.iterator();
//		
//		while( iterator1.hasNext() &&  iterator2.hasNext()){
//			String selectedAnswer = iterator1.next();
//			String correctAnswer = iterator2.next();
//			if (selectedAnswer != null) {
//				++ countAttempted;
//				if (selectedAnswer.equals(correctAnswer))
//					++ countCorrect;
//			}
//		}
//
//		TestResult result = new TestResult();
//		result.setAttemptedQuestions(countAttempted);
//		result.setTotalQuestions(correctAnswers.size());
//		result.setCorrectAnswers(countCorrect);
//		result.setPercentageScore(countCorrect * 100 / correctAnswers.size());
//		return result;
//	}
	
//	public TestResult calculateResult(QuizForm quizForm) {
//	    List<String> selectedAnswers = quizForm.getAnswers();
//	    List<Question> correctQuestions = getQuestionsForCurrentQuiz();
//
//	    if (selectedAnswers == null) {
//	        return new TestResult();
//	    }
//
//	    int countCorrect = 0;
//	    int countAttempted = 0;
//	    List<Question> feedbackQuestions = new ArrayList<>();
//
//	    Iterator<String> iterator1 = selectedAnswers.iterator();
//	    Iterator<Question> iterator2 = correctQuestions.iterator();
//
//	    while (iterator1.hasNext() && iterator2.hasNext()) {
//	        String selectedAnswer = iterator1.next();
//	        Question correctQuestion = iterator2.next();
//
//	        if (selectedAnswer != null) {
//	            ++countAttempted;
//	            if (selectedAnswer.equals(correctQuestion.getCorrectAnswer())) {
//	                ++countCorrect;
//	            } else {
//	                feedbackQuestions.add(correctQuestion);
//	            }
//	        }
//	    }
//
//	    TestResult result = new TestResult();
//	    result.setAttemptedQuestions(countAttempted);
//	    result.setTotalQuestions(correctQuestions.size());
//	    result.setCorrectAnswers(countCorrect);
//	    result.setPercentageScore(countCorrect * 100 / correctQuestions.size());
//	    result.setFeedbackQuestions(feedbackQuestions);
//
//	    return result;
//	}
	
	public TestResult calculateResult(QuizForm quizForm) {
		
		List<Question> feedbackQuestions = new ArrayList<>();
		
		List<String> userSelectedAnswers = quizForm.getUserSelectedAnswers();
		
		if (userSelectedAnswers == null) {
			return new TestResult(0, currentQuizList.size(), 
					0, 0, currentQuizList);
		}
		
		Iterator<Question> currentQuizIterator = currentQuizList.iterator();
		Iterator<String> userSelectedAnswersIterator = userSelectedAnswers.iterator();
		
		int countCorrect = 0;
	    int countAttempted = 0;
		
		while (currentQuizIterator.hasNext()) {
			Question currentQuestion = currentQuizIterator.next();

			if (userSelectedAnswersIterator.hasNext()) {
				++countAttempted;
				String userSelectedAnswer = userSelectedAnswersIterator.next();
				if (currentQuestion.getCorrectAnswer().equals(userSelectedAnswer)) {
					++countCorrect;
				}
				else {
					currentQuestion.setUserSelectedAnswer(userSelectedAnswer);
					feedbackQuestions.add(currentQuestion);
				}
			}
			else {
				currentQuestion.setUserSelectedAnswer("No option was selected.");
			}
		}
		
		return new TestResult(countAttempted, currentQuizList.size(), 
				countCorrect, countCorrect * 100 / currentQuizList.size(), 
				feedbackQuestions);
	}

}
