package org.example.whowantstobeamillionaire;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;
import org.example.whowantstobeamillionaire.functional.QuestionLoader;
import org.example.whowantstobeamillionaire.help.FiftyFiftyHelp;

public class GameController {
    @FXML
    private Button fiftyFiftyButton, phoneButton, audienceButton;
    @FXML
    private Button startButton;
    @FXML
    private Button answer1, answer2, answer3, answer4;
    @FXML
    private Label questionLabel, levelLabel, scoreLabel, timeLabel;

    private final int WIN_LEVEL = 6;
    private Question[] questions;
    private int score;
    private int level;
    private int currentQuestionIndex;

    @FXML
    public void initialize() {
        level = 1;
        score = 0;
        levelLabel.setText("Level: " + level);
        scoreLabel.setText("Score: " + score);
        questions = QuestionLoader.loadQuestions("/questions.json");

        // <-------- shuffle questions list
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);

        questions = questionList.toArray(new Question[0]);

        // <-------- hide answer buttons at start
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
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
        currentQuestionIndex = 0;
        score = 0;

        // <------ shuffle questions on every new game start
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);
        questions = questionList.toArray(new Question[0]);

        // <------ update UI labels
        scoreLabel.setText("Score: " + score);
        levelLabel.setText("Level: " + level);

        showQuestion(currentQuestionIndex);
        startButton.setDisable(true); // <------ disable start after the game starts
    }
    private void showQuestion(int index) {
        if (questions != null && index < questions.length) {
            Question q = questions[index];
            questionLabel.setText(q.getQuestionText());

            Answer[] answers = q.getAnswers();
            // <------ shuffle answers array locally
            List<Answer> shuffledAnswers = Arrays.asList(answers.clone()); // <------ clone to avoid changing original
            Collections.shuffle(shuffledAnswers);

            answer1.setText(shuffledAnswers.get(0).getText());
            answer2.setText(shuffledAnswers.get(1).getText());
            answer3.setText(shuffledAnswers.get(2).getText());
            answer4.setText(shuffledAnswers.get(3).getText());

            answer1.setUserData(shuffledAnswers.get(0));
            answer2.setUserData(shuffledAnswers.get(1));
            answer3.setUserData(shuffledAnswers.get(2));
            answer4.setUserData(shuffledAnswers.get(3));

            // <------ show answer buttons
            answer1.setVisible(true);
            answer2.setVisible(true);
            answer3.setVisible(true);
            answer4.setVisible(true);
        } else {
            questionLabel.setText("No questions available!");
        }
    }
    @FXML
    private void onAnswerClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        Answer selected = (Answer) clicked.getUserData();

        if (selected.getIsCorrect()) {
            // Correct answer: increase score & level
            score += 100;
            level += 1;

            scoreLabel.setText("Score: " + score);
            levelLabel.setText("Level: " + level);

            if (level > WIN_LEVEL) {
                // Player won the game
                questionLabel.setText("🎉 Congratulations! You won with a score of " + score + "!");
                endGame();
            } else {
                // Move to next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    showQuestion(currentQuestionIndex);
                } else {
                    // No more questions, end game anyway
                    questionLabel.setText("🎉 You completed all questions! Final score: " + score);
                    endGame();
                }
            }
        } else {
            questionLabel.setText("Wrong answer! Final score: " + score);
            endGame();
        }
    }

    @FXML
    private void onFiftyFiftyClicked() {
        Question current = questions[currentQuestionIndex];
        Answer correct = current.getCorrectAnswer();
        Answer[] wrongAnswers = current.getWrongAnswers();

        // <------ randomly pick two wrong answers to hide
        List<Answer> wrongList = new ArrayList<>(Arrays.asList(wrongAnswers));
        Collections.shuffle(wrongList);
        Answer toHide1 = wrongList.get(0);
        Answer toHide2 = wrongList.get(1);

        // <------ hide buttons based on answers
        for (Button btn : new Button[]{answer1, answer2, answer3, answer4}) {
            Answer a = (Answer) btn.getUserData();
            if (a.equals(toHide1) || a.equals(toHide2)) {
                btn.setVisible(false);
            }
        }
        // <----- disable lifeline after use
        fiftyFiftyButton.setDisable(true);
    }

    @FXML
    private void onPhoneButtonClicked() {
        Question current = questions[currentQuestionIndex];
        Answer correct = current.getCorrectAnswer();

        // <--- simulation friend's confidence 80% chance friend is sure
        double confidence = Math.random();
        String message;
        if (confidence < 0.8) {
            message = "Friend Vasile thinks the answer might be: " + correct.getText();
        } else {
            // <---- case if friend is not sure, suggest random answer wrong or correct
            Answer[] allAnswers = current.getAnswers();
            int randomIndex = (int)(Math.random() * allAnswers.length);
            message = "Friend Vasile is not sure, but maybe: " + allAnswers[randomIndex].getText();
        }

        // <--- show message
        questionLabel.setText(message);

        // <----- disable button after use
        phoneButton.setDisable(true);
    }

    private void endGame() {
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
        startButton.setDisable(false); // <------- allow restarting the game

        // <----- reset score and level to initial values
        score = 0;
        level = 1;
    }
}
