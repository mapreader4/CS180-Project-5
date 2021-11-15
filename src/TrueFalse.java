import java.io.Serializable;
import java.util.Scanner;

public class TrueFalse extends Question implements Serializable {
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
        System.out.println("[T] True");
        System.out.println("[F] False");
    }

    public String saveString(){
        return "trueFalse|"+getQuestion()+"|"+answer+"|"+getPointValue();
    }
    public void editQuestion(Scanner scanner){
        int selection;
        do {
            do {
                System.out.println("What do you want to change?");
                System.out.println("1: Change what the question is asking.");
                System.out.println("2: Change the correct answer.");
                System.out.println("3. Change point value.");
                System.out.println("4: Exit");
                selection = scanner.nextInt();
                System.out.println("Please enter a valid input!");
                scanner.nextLine();
            } while (selection != 1 || selection != 2||selection != 3);
            if(selection == 1){
                System.out.println("Please enter the updated Question.");
                this.question = scanner.nextLine();
                System.out.println("Quiz question updated!");
            }
            if(selection == 2){
                String response;
                do{
                    System.out.println("What do you want the answer to be? (True, False)");
                    response = scanner.nextLine();
                }while(response.equalsIgnoreCase("True")||response.equalsIgnoreCase("False"));
                response.toLowerCase();
                this.answer = response;
            }
            if(selection == 3){
                int points=-1;
                do {
                    System.out.println("What do you want the points for the question to be?");
                    try {
                        points = scanner.nextInt();
                        scanner.nextLine();
                    } catch (NumberFormatException e) {
                        System.out.println("An error has occurred. Please insert an integer.");
                    }
                }while(points<0); // positive points including 0;
                this.pointValue = points;
            }
        }while(selection!=4);
    }
}
