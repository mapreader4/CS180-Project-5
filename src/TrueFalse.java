import java.io.Serializable;
import java.util.Scanner;

public class TrueFalse extends Question implements Serializable {
    String answer;

    public TrueFalse(String question,String answer,int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }


    @Override
    public void displayQuestion() {
        System.out.println(this.question);
        System.out.println("[T] True");
        System.out.println("[F] False");
    }

    public String saveString(){
        return "trueFalse|"+question+"|"+answer+"|"+pointValue;
    }
}
