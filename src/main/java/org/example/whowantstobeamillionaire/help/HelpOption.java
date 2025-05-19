package org.example.whowantstobeamillionaire.help;
import org.example.whowantstobeamillionaire.functional.Question;

public abstract class HelpOption {
   private boolean isUsed;
   public boolean getIsUsed(){
       return isUsed;
    }
   // abstract method
    public abstract HelpAnswer[] getHelpAnswers(Question question);
}
