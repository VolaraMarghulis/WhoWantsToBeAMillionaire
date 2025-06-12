package org.example.whowantstobeamillionaire.help;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.whowantstobeamillionaire.functional.Answer;
import org.example.whowantstobeamillionaire.functional.Question;

import java.util.Random;

public class PhoneHelp {
    private boolean used = false;

    public boolean isUsed() {
        return used;
    }

    public void use(Question question) {
        if (used) return;
        used = true;
    }
}

