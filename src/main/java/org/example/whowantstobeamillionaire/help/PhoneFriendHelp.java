package org.example.whowantstobeamillionaire.help;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;

public class PhoneFriendHelp {
    public static String getFriendSuggestion(Question question) {
        double friendConfidence = Math.random(); // from 0.0 to 1.0 --- 0% to 100%

        if (friendConfidence < 0.8) {
            // <--- case with 80% probability gives correct answer
            return "Friend Vasile is confident it's: " + question.getCorrectAnswer().getText();
        } else {
            // <--- in case with 20% probability gives random answer
            Answer[] all = question.getAnswers();
            int index = (int)(Math.random() * all.length); // <---- pick random answer
            return "Friend Vasile is not sure, but maybe: " + all[index].getText();
        }
    }
}
