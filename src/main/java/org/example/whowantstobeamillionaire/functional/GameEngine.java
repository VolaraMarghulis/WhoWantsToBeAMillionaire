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

    public void shuffleQuestions() {
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);
        questions = questionList.toArray(new Question[0]);
    }

    public Question getCurrentQuestion() {
        if (state.getCurrentQuestionIndex() < questions.length) {
            return questions[state.getCurrentQuestionIndex()];
        }
        return null;
    }

    public boolean answer(String answerText) {
        Question q = getCurrentQuestion();
        if (q == null) return false;

        boolean correct = q.getCorrectAnswer().getText().equals(answerText);
        if (correct) {
            state.addScore(100);
            state.nextLevel();
        } else {
            state.setGameOver(true);
        }
        state.nextQuestion();
        return correct;
    }

    public boolean isGameOver() {
        return state.isGameOver() || state.getLevel() > WIN_LEVEL;
    }

    public GameState getState() {
        return state;
    }

    public void reset() {
        shuffleQuestions();
        state.reset();
    }
}
