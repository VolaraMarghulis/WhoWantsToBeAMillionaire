package org.example.whowantstobeamillionaire.help;
import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;
import java.util.Random;

public class FiftyFiftyHelp extends HelpOption{
    @Override
    public HelpAnswer[] getHelpAnswers(Question question) {
        Answer correctAnswer = question.getCorrectAnswer();
        Answer[] wrongAnswer = question.getWrongAnswers();
        Random random = new Random();
        // <------- selects one of the wrong answers from the wrongAnswers array
        Answer randomWrongAnswer = wrongAnswer[random.nextInt(wrongAnswer.length)];
        // <----- HelpAnswer objects, with space for two elements (one correct, one incorrect).
        HelpAnswer[] result = new HelpAnswer[2];
        result[0] = new HelpAnswer(correctAnswer, 0.5);
        result[1] = new HelpAnswer(randomWrongAnswer, 0.5);
        return result;
    }
}
