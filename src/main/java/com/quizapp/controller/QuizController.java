package com.quizapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizapp.model.Question;
import com.quizapp.model.QuizForm;
import com.quizapp.service.QuizService;

@Controller
public class QuizController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static List<Question> currentQuizList;
	
	@Autowired
	QuizService quizService;
	
	@RequestMapping("/home")
	public String goToHomePage() {
		return "index";
	}
	
	@GetMapping("/get-quiz")
	public String goToQuizPage(@RequestParam("subject") String quizSubject, 
			@RequestParam("testDifficulty") String quizDifficulty, Model model) {
		
		currentQuizList = quizService.getQuiz(quizSubject, quizDifficulty, 10);

		model.addAttribute("testName", quizSubject);
		model.addAttribute("quizList", currentQuizList);
	
		return "quiz";
	}
	
	@PostMapping("/submit-test")
    public String submitTest(@ModelAttribute("quizForm") QuizForm quizForm, Model model) {
		model.addAttribute("testResult", quizService.calculateResult(quizForm));
        return "result";
    }
	
}
