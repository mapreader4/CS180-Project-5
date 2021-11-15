import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Scanner;

public class MultipleChoice extends Question implements Serializable {
    int numChoices;
    String[] answerChoices;
    int correctAnswerIndex;


    public MultipleChoice(String question, int numChoices, String[] answerChoices, int answerIndex, int pointValue) {
        super(question, pointValue);
        this.numChoices = numChoices;
        this.answerChoices = answerChoices;
        this.correctAnswerIndex = answerIndex;
    }

    public int findCorrectAnswer(String answer) {
        for (int i = 0; i < numChoices; i++) {
            if (answerChoices[i].equalsIgnoreCase(answer)) {
                return i;
            }
        }
        return -1;
    }

    public void displayQuestion() {
        System.out.println(getQuestion());
        for (int i = 0; i < numChoices; i++) {
            System.out.println("[" + (i + 1) + "] " + answerChoices[i]);
        }

    }

    public String saveString() { //saves the question to a file
        String s;
        s = "multipleChoice|" + getQuestion() + "|" + numChoices + "|";
        for (int i = 0; i < answerChoices.length; i++) {
            if (i == answerChoices.length - 1) {
                s += answerChoices[i];
                break;
            }
            s += answerChoices[i] + ",";
        }
        s += "|" + correctAnswerIndex + "|" + getPointValue();
        return s;
    }

    public void editQuestion(Scanner scanner) {
        int selection;
        do {

            do {
                System.out.println("What do you want to change about this question?");
                System.out.println("1: Change what the question is asking.");
                System.out.println("2:Change answer Choices.");
                System.out.println("3:Change correct answer.");
                System.out.println("4: Change point value");
                System.out.println("5.Exit");
                selection = scanner.nextInt();
                scanner.nextLine();
                if(selection>0 && selection<6){
                    break;
                }

            } while (true);
            if(selection == 1){
                System.out.println("Please enter the updated Question.");
                this.question = scanner.nextLine();
                System.out.println("Quiz question updated!");
            }
            if(selection == 2){
                this.answerChoices = null;
                this.numChoices = 0 ;
                System.out.println("Current answer choices have been erased!");
                System.out.println("How many answer choices do you want for this question?");
                int numChoices = scanner.nextInt();
                this.numChoices = numChoices;
                scanner.nextLine();
                this.answerChoices = new String[numChoices];
                for(int i = 0 ; i < numChoices;i++){
                    System.out.println("Enter you answer choice number #"+(i+1));
                    this.answerChoices[i] = scanner.nextLine();
                }
                System.out.println("What answer choice is correct? (Please insert a Integer)");
                this.correctAnswerIndex =scanner.nextInt();
                scanner.nextLine();
            }
            if(selection == 3){
                int answerIndex;
                do{
                    for(int i = 0 ; i< answerChoices.length;i++){
                        System.out.println(i+": "+this.answerChoices[i]);
                    }
                    System.out.println("What do you want the correct answer to be");
                    answerIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("There was a problem editing the current answer choice!");
                }while(answerIndex>answerChoices.length-1);
                System.out.println("The correct answer has been updated.");

            }
            if(selection == 4){
                int points=-1;
                do {
                    System.out.println("What do you want the points for the question to be?");
                    try {
                        points = scanner.nextInt();
                        scanner.nextLine();
                    } catch (NumberFormatException e) {
                        System.out.println("An error has occurred. Please insert an integer.");
                    }
                }while(points<0);
                this.pointValue = points;
            }
        }while(selection!=5);
    }
}

