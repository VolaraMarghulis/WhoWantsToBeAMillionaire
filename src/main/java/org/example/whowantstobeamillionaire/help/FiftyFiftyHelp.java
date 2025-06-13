package org.example.whowantstobeamillionaire.help;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// <--- not use
public class FiftyFiftyHelp {

    public static Answer[] getTwoWrongAnswersToHide(Question question) {
        Answer[] wrongAnswers = question.getWrongAnswers(); // <---- take all 3 wrong answer

        if (wrongAnswers.length < 2) { // <----- in case if it's less than 2 wrong answers
            return new Answer[0]; // <--- empty array to avoid errors
        }
        // <----- Convert the array into a mutable list so it can be shuffled
        List<Answer> wrongList = new ArrayList<>(List.of(wrongAnswers));
        Collections.shuffle(wrongList);

        return new Answer[]{
                wrongList.get(0), wrongList.get(1) // return two randomly wrong answers
                };
    }
}
