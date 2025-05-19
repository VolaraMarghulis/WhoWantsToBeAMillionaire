package org.example.whowantstobeamillionaire.help;
import org.example.whowantstobeamillionaire.functional.Answer;

public class HelpAnswer {
    private Answer answer;
    private double probability;

    public HelpAnswer(Answer answer, double probability) {
        this.answer = answer;
        this.probability = probability;
    }
    //getters
    public Answer getAnswer() {
        return answer;
    }
    public double getProbability() {
        return probability;
    }
}
