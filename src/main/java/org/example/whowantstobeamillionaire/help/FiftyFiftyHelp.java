package org.example.whowantstobeamillionaire.help;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FiftyFiftyHelp {

    public static Answer[] getTwoWrongAnswersToHide(Question question) {
        Answer[] wrongAnswers = question.getWrongAnswers();

        if (wrongAnswers.length < 2) {
            return new Answer[0]; // <------ not enough to hide
        }

        List<Answer> wrongList = new ArrayList<>(List.of(wrongAnswers));
        Collections.shuffle(wrongList);

        return new Answer[]
                {wrongList.get(0), wrongList.get(1)
                };
    }
}
