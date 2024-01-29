package com.quizapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizapp.config.QuizConfig;
import com.quizapp.model.Question;

@Service
public class QuizService {
	
	@Autowired
	QuizConfig quizConfig;
	
	public List<Question> getQuiz() {
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            Resource resource = new ClassPathResource("files/sample.json");
            InputStream inputStream = resource.getInputStream();
            
            return objectMapper.readValue(inputStream, new TypeReference<List<Question>>() {});
        } catch (IOException e) {
            // Handle exceptions (cases : file not found, invalid JSON)
            e.printStackTrace();
            return Collections.emptyList();
        }
	}
}
