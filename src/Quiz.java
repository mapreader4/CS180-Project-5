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
        creatQuizByInputIndividually(scanner);
    }
    public Quiz(Scanner scanner,boolean temp){
        String filePath=FileImports.prompt();
        ArrayList<String> questionList=FileImports.readFile(filePath);
        createQuizFromFile(questionList, scanner);
    }
    public void createQuizFromFile(ArrayList<String> questionList, Scanner scanner){
        for(int i=1; i<questionList.size();i++){
            System.out.println();
            System.out.println("How many points do you want this question to be?");
            int points=Integer.parseInt(scanner.nextLine());
            System.out.println("What type of question is this?");
            System.out.println("Options: True False, Fill in the blank, Multiple Choice");
            String questionType;
            do {
                questionType = scanner.nextLine();
                if (!questionType.equalsIgnoreCase("True False") && !questionType.equalsIgnoreCase("Fill in the blank") && !questionType.equalsIgnoreCase("Multiple Choice")){
                    System.out.println("Please enter a valid type of question");
                    continue;
                }
                break;
            } while (true);
            if(questionType.equalsIgnoreCase("True False")){
                System.out.println("What is the correct answer?");
                String answer = scanner.nextLine();
                Question q = new TrueFalse(questionList.get(i),answer,points);
                quiz.add(q);

            }else if(questionType.equalsIgnoreCase("Fill in the blank")){
                System.out.println("What is the correct answer?");
                String answer = scanner.nextLine();
                Question q = new FillInTheBlank(questionList.get(i),answer,points);
                quiz.add(q);
            }

            else if(questionType.equalsIgnoreCase("Multiple Choice")){
                System.out.println("Enter the amount of answer choices for this question?");
                int answerChoices;
                do {
                    answerChoices = scanner.nextInt();
                    scanner.nextLine();
                    if (answerChoices<1){
                        System.out.println("Invalid input, enter amount of choices for this question again");
                        continue;
                    }
                    break;
                } while(true);
                String[]choices = new String[answerChoices];
                for(int j = 0; j <answerChoices;j++){
                    System.out.println("Enter the Answer choice #"+(j+1));
                    choices[j] = scanner.nextLine();
                }
                System.out.println("What answer choice is correct?");
                int answerIndex;
                do {
                    answerIndex = (scanner.nextInt()) + 1;
                    scanner.nextLine();
                    if(answerIndex<0 || answerIndex>answerChoices){
                        System.out.println("Enter the correct answer index");
                        break;
                    }
                    continue;
                } while(true);
                Question q = new MultipleChoice(questionList.get(i),answerChoices,choices,answerIndex,points);
                quiz.add(q);
            }
        }
    }

    public void creatQuizByInputIndividually(Scanner scanner){
        System.out.println("What do you want to name this quiz?");
        String name = scanner.nextLine();
        this.name = name;
        int questions;
        do {
            System.out.println("How many Questions do you want your Quiz to have?");
            questions = scanner.nextInt();
            scanner.nextLine();
            if (questions<1){
                continue;
            }
            break;
        } while(true);
        this.numQuestions=questions;
        for(int i = 0 ; i <questions ; i++){
            //String type, String question, int numChoices, String[]answerChoices, int correctAnswerIndex
            System.out.println("What type of question would you like Question #"+(i+1)+" to be?");
            System.out.println(("Options: True False, Fill in the blank, Multiple Choice"));
            String questionType;
            do {
                questionType = scanner.nextLine();
                if (!questionType.equalsIgnoreCase("True False") && !questionType.equalsIgnoreCase("Fill in the blank") && !questionType.equalsIgnoreCase("Multiple Choice")){
                    System.out.println("Please enter a valid type of question");
                    continue;
                }
                break;
            } while (true);
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
                int answerChoices;
                do {
                    answerChoices = scanner.nextInt();
                    scanner.nextLine();
                    if (answerChoices<1){
                        System.out.println("Invalid input, enter amount of choices for this question again");
                        continue;
                    }
                    break;
                } while(true);
                String[]choices = new String[answerChoices];
                for(int j = 0; j <answerChoices;j++){
                    System.out.println("Enter the Answer choice #"+(j+1));
                    choices[j] = scanner.nextLine();
                }
                System.out.println("What answer choice is correct?");
                int answerIndex;
                do {
                    answerIndex = (scanner.nextInt()) + 1;
                    scanner.nextLine();
                    if(answerIndex<0 || answerIndex>answerChoices){
                        System.out.println("Enter the correct answer index");
                        break;
                    }
                    continue;
                } while(true);
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




