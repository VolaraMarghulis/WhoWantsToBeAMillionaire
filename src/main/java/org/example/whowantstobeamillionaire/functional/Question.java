package org.example.whowantstobeamillionaire.functional;
import org.example.whowantstobeamillionaire.functional.Answer;

public class Question {
    private String questionText;
    private Answer[] answers;

    public Question(String questionText, Answer[] answers) {
        this.questionText = questionText;
        this.answers = answers;
    }
    //getters
    public String getQuestionText() {
        return questionText;
    }

    public Answer[] getAnswers() {
        return answers;
    }
    // <------ return correct answer and check if is not null
    public Answer getCorrectAnswer() {
        for (Answer answer : answers) {
            if (answer != null && answer.getIsCorrect()) return answer;
        }
        return null;
    }

    // <------ returns all wrong answers in a new array
    public Answer[] getWrongAnswers() {
        int count = 0;
        // Count wrong answers to create new array[count length]
        for (Answer answer : answers) {
            if (answer != null && !answer.getIsCorrect()) {
                count++;
            }
        }

        Answer[] wrongs = new Answer[count];
        int i = 0;
        for (Answer answer : answers) {
            if (answer != null && !answer.getIsCorrect()) {
                wrongs[i++] = answer;
            }
        }
        return wrongs;
    }
    // <------ return by index, for future implementation
    public int getCorrectIndex() {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].getIsCorrect()) return i;
        }
        return -1;
    }
}
