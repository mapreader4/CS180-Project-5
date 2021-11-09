import java.util.Scanner;

public class FillInTheBlank extends Question {
    String answer;

    public FillInTheBlank(String question, String answer, int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }

    @Override
    void displayQuestion() {

    }

    public FillInTheBlank(String question, int pointValue){
        super(question,pointValue);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the answer to the question?");
        String line = scanner.nextLine();
        this.answer = answer;
    }

    public String saveString(){
        return "fillInTheBlank|"+question+"|"+answer+"|"+pointValue;
    }

}
