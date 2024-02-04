package com.quizapp.utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.quizapp.model.TestResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestResultLogger {

    private static final String FILE_PATH = "test_results.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void logTestResult(TestResult result) {
        try {
            List<TestResult> existingResults = readTestResults();
            existingResults.add(result);

            objectMapper.writeValue(new File(FILE_PATH), existingResults);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public static List<TestResult> readTestResults() {
        List<TestResult> results = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, TestResult.class);
                results = objectMapper.readValue(file, collectionType);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return results;
    }
}
