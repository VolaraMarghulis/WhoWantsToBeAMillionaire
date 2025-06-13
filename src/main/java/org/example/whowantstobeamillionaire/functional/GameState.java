package org.example.whowantstobeamillionaire.functional;

public class GameState {
    private int currentQuestionIndex;
    private int score;
    private int level;
    private boolean isGameOver;

    public GameState() {
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.level = 1;
        this.isGameOver = false;
    }
    // <------ getters
    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public void setGameOver(boolean value) { isGameOver = value; } // <----- setter
    public boolean isGameOver() { return isGameOver; }
    // <------ adding method
    public void nextQuestion() { currentQuestionIndex++; }
    public void addScore(int amount) { score += amount; }
    public void nextLevel() { level++; }
    // <---- reset all states
    public void reset() {
        currentQuestionIndex = 0;
        score = 0;
        level = 1;
        isGameOver = false;
    }
}
