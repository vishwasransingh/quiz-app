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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quizapp.model.Question;
import com.quizapp.model.QuizForm;
import com.quizapp.model.TestResult;
import com.quizapp.model.User;
import com.quizapp.service.AuthService;
import com.quizapp.service.QuizService;
import com.quizapp.utility.TestResultLogger;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static List<Question> currentQuizList;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	AuthService authService;
	
	@RequestMapping("/home")
	public String goToHomePage(HttpSession session, RedirectAttributes redirectAttributes) {
	    if (!authService.verifyAuth(session)) {
	        session.invalidate();
	        redirectAttributes.addFlashAttribute("errorMessage", "Please try that with credentials.");
	        return "redirect:/login";
	    }

	    return "home";
	}
	
	@GetMapping("/get-quiz")
	public String goToQuizPage(@RequestParam("subject") String quizSubject, 
			@RequestParam("testDifficulty") String quizDifficulty, Model model,
			@RequestParam("numberOfQuestions") int numberOfQuestions) {
		
		currentQuizList = quizService.getQuiz(quizSubject, quizDifficulty, numberOfQuestions);

		model.addAttribute("testName", quizSubject);
		model.addAttribute("quizList", currentQuizList);
	
		return "quiz";
	}
	
	@PostMapping("/submit-test")
    public String submitTest(@ModelAttribute("quizForm") QuizForm quizForm, Model model, HttpSession session) {
		TestResult testResult = quizService.calculateResult(quizForm, (User)session.getAttribute("user"));
		TestResultLogger.logTestResult(testResult);
		model.addAttribute("testResult", testResult);
        return "result";
    }
	
}
