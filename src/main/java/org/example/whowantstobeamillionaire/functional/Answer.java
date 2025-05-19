package org.example.whowantstobeamillionaire.functional;

public class Answer {
    private String text;
    private boolean isCorrect;

    public String getText() { return text; }
    public boolean getIsCorrect() { return isCorrect; }

    // setters required for Jackson
    public void setText(String text) { this.text = text; }
    public void setIsCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
}
