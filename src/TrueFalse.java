import java.io.Serializable;

public class TrueFalse extends Question{
    String answer;
    public String getAnswer(){
        return this.answer;
    }
    public TrueFalse(String question,String answer,int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }


    public void displayQuestion() {
        System.out.println(this.getQuestion());
        System.out.println("True");
        System.out.println("False");
    }

    public String saveString(){
        return "trueFalse|"+getQuestion()+"|"+answer+"|"+getPointValue();
    }
}
