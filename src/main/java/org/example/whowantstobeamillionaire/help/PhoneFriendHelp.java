package org.example.whowantstobeamillionaire.help;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;

public class PhoneFriendHelp {
    public static String getFriendSuggestion(Question question) {
        double confidence = Math.random();

        if (confidence < 0.8) {
            return "Friend Vasile is confident it's: " + question.getCorrectAnswer().getText();
        } else {
            Answer[] all = question.getAnswers();
            int index = (int)(Math.random() * all.length);
            return "Friend Vasile is not sure, but maybe: " + all[index].getText();
        }
    }
}
