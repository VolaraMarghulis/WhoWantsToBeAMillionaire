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
import org.example.whowantstobeamillionaire.help.FiftyFiftyHelp;
import org.example.whowantstobeamillionaire.help.PhoneFriendHelp;

public class GameController {
    @FXML private Button fiftyFiftyButton, phoneButton, audienceButton;
    @FXML private Button startButton;
    @FXML private Button answer1, answer2, answer3, answer4;
    @FXML private Label questionLabel, levelLabel, scoreLabel, timeLabel;

    private GameEngine engine; // <---- game logic

    @FXML
    public void initialize() {
        // <----- load questions from JSON file
        Question[] questions = QuestionLoader.loadQuestions("/questions.json");
        engine = new GameEngine(questions); // <---- create game engine instance

        hideAnswers(); // <---- hide answer buttons until game starts
        questionLabel.setText("Press Start to play!");
        // <------- set event handlers for fyftyFyfty/phoneFriend
        fiftyFiftyButton.setOnAction(e -> onFiftyFiftyClicked());
        phoneButton.setOnAction(e -> onPhoneButtonClicked());
        // <----- Set event handlers for answer buttons
        answer1.setOnAction(this::onAnswerClicked);
        answer2.setOnAction(this::onAnswerClicked);
        answer3.setOnAction(this::onAnswerClicked);
        answer4.setOnAction(this::onAnswerClicked);
    }

    @FXML
    private void onStartButtonClicked() { // <----- called when Start button is clicked
        engine.reset(); // <------- reset from last game (in case)
        updateScoreAndLevel(); // <------- show updated score and level
        showQuestion(); // <------- display the first question
        startButton.setDisable(true); // <------- disable Start button during the game
    }
    // <----- displays the current question and shuffles answers before showing
    private void showQuestion() {
        Question question = engine.getCurrentQuestion();

        if (question == null) {
            questionLabel.setText("No questions available!");
            return;
        }

        questionLabel.setText(question.getQuestionText()); // <----- set question text
        List<Answer> shuffled = Arrays.asList(question.getAnswers().clone());
        Collections.shuffle(shuffled); // <------ // shuffle answers

        // <----- assign shuffled answers to buttons and make sure they are visible
        Button[] buttons = {answer1, answer2, answer3, answer4};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(shuffled.get(i).getText());
            buttons[i].setUserData(shuffled.get(i));
            buttons[i].setVisible(true);
        }
    }

    @FXML
    // <----  checks if the selected answer is correct and updates game state
    private void onAnswerClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        Answer selected = (Answer) clicked.getUserData();

        boolean correct = engine.answer(selected.getText()); // <---- // check the answer with the game engine
        updateScoreAndLevel();
        // <---- Check if the game ended after this answer
        if (engine.isGameOver()) {
            // <------ show final message depending on case
            questionLabel.setText(correct ? "You won! \n Final score: " + engine.getState().getScore()
                    : "Wrong answer! Final score: " + engine.getState().getScore());
            endGame();  //  <------ // clean up UI after game over
        } else {
            showQuestion(); // <----- // show next question if game continues
        }
    }

    // <----- hides two incorrect answers from the current question
    private void onFiftyFiftyClicked() {
        Question current = engine.getCurrentQuestion();
        Answer[] toHide = FiftyFiftyHelp.getTwoWrongAnswersToHide(current);
        // <------ hide the buttons that correspond to the two answers
        for (Button btn : new Button[]{answer1, answer2, answer3, answer4}) {
            Answer a = (Answer) btn.getUserData();
            if (a.equals(toHide[0]) || a.equals(toHide[1])) {
                btn.setVisible(false);
            }
        }
        fiftyFiftyButton.setDisable(true); // <------- disable 50/50 button after use
    }

    // <-------- shows a friend’s suggestion as a hint in the question label
    private void onPhoneButtonClicked() {
        Question current = engine.getCurrentQuestion();
        String message = PhoneFriendHelp.getFriendSuggestion(current);
        questionLabel.setText(message);
        phoneButton.setDisable(true); // <----- disable phone help after use
    }

    private void updateScoreAndLevel() {
        scoreLabel.setText("Score: " + engine.getState().getScore());
        levelLabel.setText("Level: " + engine.getState().getLevel());
    }

    // <----- hides answer buttons and re-enables the Start button.
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
