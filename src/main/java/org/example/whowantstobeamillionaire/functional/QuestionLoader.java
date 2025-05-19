package org.example.whowantstobeamillionaire.functional;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class QuestionLoader {
    public static Question[] loadQuestions(String resourcePath) {
        try (InputStream is = QuestionLoader.class.getResourceAsStream(resourcePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, Question[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return new Question[0];
        }
    }
}
