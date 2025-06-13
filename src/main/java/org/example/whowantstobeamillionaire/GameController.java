package org.example.whowantstobeamillionaire;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.GameEngine;
import org.example.whowantstobeamillionaire.functional.Question;
import org.example.whowantstobeamillionaire.functional.QuestionLoader;

public class GameController {
    @FXML private Button fiftyFiftyButton, phoneButton, audienceButton;
    @FXML private Button startButton;
    @FXML private Button answer1, answer2, answer3, answer4;
    @FXML private Label questionLabel, levelLabel, scoreLabel, timeLabel;

    private GameEngine engine;

    @FXML
    public void initialize() {
        Question[] questions = QuestionLoader.loadQuestions("/questions.json");
        engine = new GameEngine(questions);

        hideAnswers();
        questionLabel.setText("Press Start to play!");

        fiftyFiftyButton.setOnAction(e -> onFiftyFiftyClicked());
        phoneButton.setOnAction(e -> onPhoneButtonClicked());

        answer1.setOnAction(this::onAnswerClicked);
        answer2.setOnAction(this::onAnswerClicked);
        answer3.setOnAction(this::onAnswerClicked);
        answer4.setOnAction(this::onAnswerClicked);
    }

    @FXML
    private void onStartButtonClicked() {
        engine.reset();
        updateScoreAndLevel();
        showQuestion();
        startButton.setDisable(true);
    }

    private void showQuestion() {
        Question q = engine.getCurrentQuestion();
        if (q == null) {
            questionLabel.setText("No questions available!");
            return;
        }

        questionLabel.setText(q.getQuestionText());
        List<Answer> shuffled = Arrays.asList(q.getAnswers().clone());
        Collections.shuffle(shuffled);

        Button[] buttons = {answer1, answer2, answer3, answer4};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(shuffled.get(i).getText());
            buttons[i].setUserData(shuffled.get(i));
            buttons[i].setVisible(true);
        }
    }

    @FXML
    private void onAnswerClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        Answer selected = (Answer) clicked.getUserData();

        boolean correct = engine.answer(selected.getText());
        updateScoreAndLevel();

        if (engine.isGameOver()) {
            questionLabel.setText(correct ? "🎉 You won! Final score: " + engine.getState().getScore()
                    : "Wrong answer! Final score: " + engine.getState().getScore());
            endGame();
        } else {
            showQuestion();
        }
    }

    private void onFiftyFiftyClicked() {
        Question current = engine.getCurrentQuestion();
        Answer[] wrongs = current.getWrongAnswers();
        Collections.shuffle(Arrays.asList(wrongs));

        for (Button btn : new Button[]{answer1, answer2, answer3, answer4}) {
            Answer a = (Answer) btn.getUserData();
            if (a.equals(wrongs[0]) || a.equals(wrongs[1])) {
                btn.setVisible(false);
            }
        }
        fiftyFiftyButton.setDisable(true);
    }

    private void onPhoneButtonClicked() {
        Question current = engine.getCurrentQuestion();
        double confidence = Math.random();
        String suggestion = confidence < 0.8 ? current.getCorrectAnswer().getText()
                : current.getAnswers()[(int)(Math.random() * 4)].getText();
        questionLabel.setText("Friend suggests: " + suggestion);
        phoneButton.setDisable(true);
    }

    private void updateScoreAndLevel() {
        scoreLabel.setText("Score: " + engine.getState().getScore());
        levelLabel.setText("Level: " + engine.getState().getLevel());
    }

    private void endGame() {
        hideAnswers();
        startButton.setDisable(false);
    }

    private void hideAnswers() {
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
    }
}
