import java.io.Serializable;
import java.util.Scanner;

public class FillInTheBlank extends Question implements Serializable {
    String answer;

    public FillInTheBlank(String question, String answer, int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }

    @Override
    void displayQuestion() {

    }

    public String saveString(){
        String s = "fillInTheBlank|"+question+"|"+answer+"|"+pointValue;
        return s;
    }

}
