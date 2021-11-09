import java.util.Scanner;

public class TrueFalse extends Question {
    String answer;

    public TrueFalse(String question,String answer,int pointValue){
        super(question,pointValue);
        this.answer = answer;
    }
    public TrueFalse(String question, int points){
        super(question,points);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the correct answer to this Question?");
        String answer = scanner.nextLine();
        if(!(answer.equalsIgnoreCase("false"))||!answer.equalsIgnoreCase("true")){
            System.out.println("There was an error creating a True or false question!");
        }
        scanner.close();
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
