package org.example.whowantstobeamillionaire;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.whowantstobeamillionaire.functional.Question;
import org.example.whowantstobeamillionaire.functional.QuestionLoader;

public class GameController {
    @FXML
    private Button fiftyFiftyButton, phoneButton, audienceButton;
    @FXML
    private Button startButton;
    @FXML
    private Button answer1, answer2, answer3, answer4;
    @FXML
    private Label questionLabel , timeLabel;
    private Question[] questions;

    private int currentQuestionIndex = 0;

    @FXML
    public void initialize() {
        questions = QuestionLoader.loadQuestions("/questions.json");
        // <-------- hide answer buttons at start
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
        questionLabel.setText("Press Start to play!");
    }
    @FXML
    private void onStartButtonClicked() {
        currentQuestionIndex = 0;
        showQuestion(currentQuestionIndex);
        startButton.setDisable(true); // Optional: disable start after the game starts
    }
    private void showQuestion(int index) {
        if (questions != null && index < questions.length) {
            Question q = questions[index];
            questionLabel.setText(q.getQuestionText());
            answer1.setText(q.getAnswers()[0].getText());
            answer2.setText(q.getAnswers()[1].getText());
            answer3.setText(q.getAnswers()[2].getText());
            answer4.setText(q.getAnswers()[3].getText());
            // Show answer buttons
            answer1.setVisible(true);
            answer2.setVisible(true);
            answer3.setVisible(true);
            answer4.setVisible(true);
        } else {
            questionLabel.setText("No questions available!");
        }
    }
}