package com.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.model.User;
import com.quizapp.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
	@Autowired
	AuthService authService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session, Model model) {
    	try {
			if (authService.authenticateUser(user.getUserName(), user.getPassword())) {
				session.setAttribute("user", user);
				return "redirect:/home";
			}
		} catch (Exception e) {
			System.out.println("Exception thrown" );
			e.printStackTrace();
		}
    	session.invalidate();
    	model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
    	return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}