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

            else if(questionType.equalsIgnoreCase("MultipleChoice")){
                System.out.println("Enter the amount of answer choices for this question?");
                int answerChoices = scanner.nextInt();
                scanner.nextLine();
                String[]choices = new String[answerChoices];
                for(int j = 0; j <answerChoices;j++){
                    System.out.println("Enter the Answer choice #"+(j+1));
                    choices[j] = scanner.nextLine();
                }
                System.out.println("What answer choice is correct?");
                int answerIndex = scanner.nextInt();
                scanner.nextLine();
                Question q = new MultipleChoice(question,answerChoices,choices,answerIndex,points);
                quiz.add(q);

            }
        }
        System.out.println("Congrats, you created the quiz titled "+name+".");


    }
    public Quiz(String filename){//creates Quiz from file (created from previous runs)
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            int i = 0;
            while((line = reader.readLine())!=null){
                if(i ==0){
                    this.name = line;
                }else {
                    Question q = null;
                    String[] splitUp = line.split("|");
                    String type = splitUp[0];
                    String question = null;
                    int answerChoices;
                    String answer = null;
                    int pointValue = Integer.parseInt(splitUp[5]);
                    if(type.equalsIgnoreCase("True False")){ //split in 4 sections(type|question|answer|pointValue) 0-3
                        question = splitUp[1];
                        answer = splitUp[2];
                        pointValue = Integer.parseInt(splitUp[3]);
                       q = new TrueFalse(question,answer,pointValue);
                    }else if(type.equalsIgnoreCase("Fill in the blank")){ //split in 4 sections(type|question|answer|pointValue) 0-3
                        question = splitUp[1];
                        answer = splitUp[2];
                        pointValue = Integer.parseInt(splitUp[3]);
                        q = new FillInTheBlank(question,answer, pointValue);
                    }else if(type.equalsIgnoreCase("Multiple Choice")){//split in 6 sections(type|question|numChoices|Choices[]|answer|pointValue) 0-5
                        question = splitUp[1];
                        answerChoices = Integer.parseInt(splitUp[2]);
                        String[] choices = splitUp[3].split(",");
                        int index = Integer.parseInt(splitUp[4]);
                        pointValue = Integer.parseInt(splitUp[5]);
                        q= new MultipleChoice(question,answerChoices, choices,index,pointValue);
                    }
                    quiz.add(q);
                }
                i++;
            }
        }catch (IOException e){
            System.out.println("There was a problem creating Quiz object from filename");
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Quiz(scanner);

    }
}



