package org.example.whowantstobeamillionaire.functional;

public class Question {
    private String questionText;
    private Answer[] answers;

    // <---- getters
    public String getQuestionText() { return questionText; }
    public Answer[] getAnswers() { return answers; }

    // <------ return correct answer and check if is not null
    public Answer getCorrectAnswer() {
        for (Answer answer : answers) {
            if (answer != null && answer.getIsCorrect()) {
                return answer; // <----- correct answer
            }
        }
        return null; // <---- null if no correct answer
    }

    // <------ returns all wrong answers in a new array
    public Answer[] getWrongAnswers() {
        int count = 0; // <----  count how many answers are wrong

        for (Answer answer : answers) {
            if (answer != null && !answer.getIsCorrect()) { // <---- how many answers are wrong (not null and !correct)
                count++;
            }
        }
        Answer[] wrongs = new Answer[count]; // <---- array with wrong answers
        int i = 0;
        for (Answer answer : answers) {
            if (answer != null && !answer.getIsCorrect()) {
                wrongs[i++] = answer;
            }
        }
        return wrongs;
    }

}
