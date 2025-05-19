package org.example.whowantstobeamillionaire.functional;
import org.example.whowantstobeamillionaire.functional.Answer;

public class Question {
    private String questionText;
    private Answer[] answers;

    public String getQuestionText() { return questionText; }
    public Answer[] getAnswers() { return answers; }

    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public void setAnswers(Answer[] answers) { this.answers = answers; }
}
