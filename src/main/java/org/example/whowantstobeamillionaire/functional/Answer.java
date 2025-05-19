package org.example.whowantstobeamillionaire.functional;

public class Answer {
    private String text;
    private boolean isCorrect;

    public String getText() { return text; }
    public boolean isCorrect() { return isCorrect; }

    // setters required for Jackson or Gson
    public void setText(String text) { this.text = text; }
    public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
}
