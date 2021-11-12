import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz implements Serializable {
    private int numQuestions;
    private ArrayList<Question> quiz = new ArrayList<Question>();
    private String pathway;
    private String name;

    public Quiz(Scanner scanner){ //create Quiz within the Quiz Class
        System.out.println("What do you want to name this quiz?");
        String name = scanner.nextLine();
        this.name = name;
        System.out.println("How many Questions do you want your Quiz to have?");
        int questions = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0 ; i <questions ; i++){
            //String type, String question, int numChoices, String[]answerChoices, int correctAnswerIndex
            System.out.println("What type of question would you like Question #"+(i+1)+" to be?");
            System.out.println(("Options: True False, Fill in the blank, Multiple Choice"));
            String questionType = scanner.nextLine();
            System.out.println("What do you want question "+(i+1)+" to ask?");
            String question = scanner.nextLine();
            System.out.println("How many points do you want this question to be?");
            int points = scanner.nextInt();
            scanner.nextLine();
            if(questionType.equalsIgnoreCase("True False")){
                System.out.println("What is the correct answer?");
                String answer = scanner.nextLine();
                Question q = new TrueFalse(question,answer,points);
                quiz.add(q);

            }else if(questionType.equalsIgnoreCase("Fill in the blank")){
                System.out.println("What is the correct answer?");
                String answer = scanner.nextLine();
                Question q = new FillInTheBlank(question,answer,points);
                quiz.add(q);

            }

            else if(questionType.equalsIgnoreCase("Multiple Choice")){
                System.out.println("Enter the amount of answer choices for this question?");
                int answerChoices = scanner.nextInt();
                scanner.nextLine();
                String[]choices = new String[answerChoices];
                for(int j = 0; j <answerChoices;j++){
                    System.out.println("Enter the Answer choice #"+(j+1));
                    choices[j] = scanner.nextLine();
                }
                System.out.println("What answer choice is correct?");
                int answerIndex = (scanner.nextInt())+1;
                scanner.nextLine();
                Question q = new MultipleChoice(question,answerChoices,choices,answerIndex,points);
                quiz.add(q);
            }
        }
        System.out.println("Congrats, you created the quiz titled "+name+".");
    }
    
    public String getName() {
        return name;
    }
    public ArrayList<Question> getQuiz(){
        return quiz;
    }
}



