package org.example.whowantstobeamillionaire.help;
import org.example.whowantstobeamillionaire.functional.Question;
import org.example.whowantstobeamillionaire.functional.Answer;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class AudienceHelp extends HelpOption{
    @Override
    // <------ get the possible answers for the question
    public HelpAnswer[] getHelpAnswers(Question question) {
        Answer[] answers = question.getAnswers();
        int n = answers.length;
        double[] probabilities = new double[n];
        Random rand = new Random();
        int correctIdx = -1;

    // <------ find the index of the correct answer
        for (int i = 0; i < n; i++) {
            if (answers[i].getIsCorrect()) {
                correctIdx = i;
                break;
            }
        }
    // <------ generate a high probability for the correct answer (between 50% and 80%)
        double maxProb = 0.5 + rand.nextDouble() * 0.3; // 0.5 - 0.8
        probabilities[correctIdx] = maxProb;
        double rest = 1.0 - maxProb; // <----- the remaining probability to be distributed among the wrong answers
        double sum = 0.0;
    // <------ generate random probabilities for the wrong answers, at least 10% each
        for (int i = 0; i < n; i++) {
            if (i != correctIdx) {
                probabilities[i] = 0.1 + rand.nextDouble() * (rest / (n - 1));
                sum += probabilities[i];
            }
        }

    // <------ adjust the probabilities so the total sum is exactly 1.0
        double adjust = (rest - sum) / (n - 1);
        for (int i = 0; i < n; i++) {
            if (i != correctIdx) probabilities[i] += adjust;
        }

    // <------ build the result array with HelpAnswer (answer + probability)
        HelpAnswer[] result = new HelpAnswer[n];
        for (int i = 0; i < n; i++) {
            result[i] = new HelpAnswer(answers[i], probabilities[i]);
        }

    // <------ shuffle the HelpAnswer array so the order is not always the same
        ArrayList<HelpAnswer> list = new ArrayList<>();
        for (HelpAnswer ha : result) list.add(ha);
        Collections.shuffle(list);
    // <------ return the result as an array
        return list.toArray(new HelpAnswer[0]);
    }
}
