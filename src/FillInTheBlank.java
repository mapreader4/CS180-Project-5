import java.io.Serializable;
public class FillInTheBlank extends Question implements Serializable {
    String answer;
    public String getAnswer(){
        return this.answer;
    }

    public FillInTheBlank(String question, String answer, int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }

    public void displayQuestion() {
        System.out.println(question);
    }

    public String saveString(){
        String s = "fillInTheBlank|"+question+"|"+answer+"|"+pointValue;
        return s;
    }

}
