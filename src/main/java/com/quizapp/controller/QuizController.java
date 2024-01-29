package com.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizapp.model.Question;
import com.quizapp.service.QuizService;

@Controller
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@RequestMapping("/home")
	public String goToHomePage() {
		return "index";
	}
	
	@RequestMapping("/quiz")
	public String goToQuizPage() {
		
		List<Question> quizList = quizService.getQuiz();
		quizList.stream().forEach(System.out :: println);
		
		return "quiz";
	}
}
