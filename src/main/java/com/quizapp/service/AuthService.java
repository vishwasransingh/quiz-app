package com.quizapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizapp.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    private static final Map<String, User> userDatabase = new HashMap<>();
    
    static {
    	userDatabase.put("admin", new User("admin", "12345"));
    }

    public void addUser(User user) {
        userDatabase.put(user.getUserName(), user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userDatabase.get(username);
        
        if (user != null && user.getPassword().equals(password)) {
        	return true;
        }
        
        return false;
    }
	
    public boolean verifyAuth(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");

            if (user != null && isValidUser(user)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidUser(User user) {
        return user.getUserName() != null && user.getPassword() != null;
    }


    
    
}
