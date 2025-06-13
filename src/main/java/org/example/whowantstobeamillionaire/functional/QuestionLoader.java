package org.example.whowantstobeamillionaire.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
// I/O
public class QuestionLoader {
    public static Question[] loadQuestions(String resourcePath) {
        try (InputStream is = QuestionLoader.class.getResourceAsStream(resourcePath)) {
            // <----- log to check if it can read
            System.out.println(is == null ? "InputStream is null!" : "InputStream is OK!");
            // <----- create a Jackson ObjectMapper instance for JSON parsing
            ObjectMapper mapper = new ObjectMapper();
            // <----- deserialize the JSON content into an array of Question objects
            Question[] arr = mapper.readValue(is, Question[].class);
            // <----- log to view length of questions
            System.out.println("Loaded " + arr.length + " questions.");
            return arr; // <----  loaded array of questions
        } catch (Exception e) { // <---- in cases of exception
            e.printStackTrace();
            return new Question[0]; // <------ empty array to prevent crashes in the calling code
        }
    }
}
