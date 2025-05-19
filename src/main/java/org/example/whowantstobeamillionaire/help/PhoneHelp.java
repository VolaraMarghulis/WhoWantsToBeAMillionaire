package org.example.whowantstobeamillionaire.help;
import org.example.whowantstobeamillionaire.functional.Question;
import org.example.whowantstobeamillionaire.functional.Answer;

public class PhoneHelp extends HelpOption{
    @Override
    public HelpAnswer[] getHelpAnswers(Question question) {
        Answer correct = question.getCorrectAnswer();
        return new HelpAnswer[]{
                new HelpAnswer(correct, 0.95)
        };
    }
}
