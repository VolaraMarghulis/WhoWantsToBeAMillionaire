package org.example.whowantstobeamillionaire.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class QuestionLoader {
    public static Question[] loadQuestions(String resourcePath) {
        try (InputStream is = QuestionLoader.class.getResourceAsStream(resourcePath)) {
            // <----- log to check if it can read
            System.out.println(is == null ? "InputStream is null!" : "InputStream is OK!");
            ObjectMapper mapper = new ObjectMapper();
            Question[] arr = mapper.readValue(is, Question[].class);
            // <----- log to view length of questions
            System.out.println("Loaded " + arr.length + " questions.");
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return new Question[0];
        }
    }
}
