package org.example.whowantstobeamillionaire.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameEngine {
    private Question[] questions;
    private GameState state;
    private final int WIN_LEVEL = 6;

    public GameEngine(Question[] questions) {
        this.questions = questions;
        shuffleQuestions();
        this.state = new GameState();
    }
    // <------ method to shuffle, work every time when is start new game
    public void shuffleQuestions() {
        List<Question> questionList = Arrays.asList(questions); // <-- important to convert, to List, necessary to use Collection.shuffle
        Collections.shuffle(questionList);  // <----- does the magic under hood
        questions = questionList.toArray(new Question[0]);
    }

    public Question getCurrentQuestion() {
        if (state.getCurrentQuestionIndex() < questions.length) { // <----- to avoid an ArrayIndexOutOfBoundsException
            return questions[state.getCurrentQuestionIndex()]; // <----- access current index
        }
        return null;
    }
    // <----- processes the user's answer to the current question
    public boolean answer(String answerText) {
        Question question = getCurrentQuestion(); // <----- question at index from array
        if (question == null) return false; // <----- not questions, not correct answers

        boolean correct = question.getCorrectAnswer().getText().equals(answerText); // return an answer object and compare to correct answer
        if (correct) {
            state.addScore(100);
            state.nextLevel(); // <---- level up
        } else {
            state.setGameOver(true); // <---- game over
        }
        state.nextQuestion(); // <---- move to next question
        return correct;
    }

    public boolean isGameOver() {
        return (state.isGameOver() || state.getLevel() > WIN_LEVEL);
        // <--- game over if loose  or  game over if win
    }
    // <---- state of game
    public GameState getState() {
        return state;
    }
    // <---- reset game
    public void reset() {
        shuffleQuestions();
        state.reset();
    }
}
