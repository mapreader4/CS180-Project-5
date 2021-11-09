import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz implements Serializable {
    private int numQuestions;
    private ArrayList<Question> quiz;
    private String pathway;
    private String name;

    public Quiz(){ //create Quiz within the Quiz Class
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to name this quiz?");
        String name = scanner.nextLine();
        this.name = name;
        System.out.println("How many Questions do you want your Quiz to have?");
        int questions = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0 ; i <questions ; i++){
            //String type, String question, int numChoices, String[]answerChoices, int correctAnswerIndex
            System.out.println("What type of question would you like Question #"+i+" to be?");
            System.out.println(("Options: True False, Fill in the blank, Multiple Choice"));
            String questionType = scanner.nextLine();
            System.out.println("What do you want question "+(i+1)+" to ask?");
            String question = scanner.nextLine();
            System.out.println("How many points do you want this question to be?");
            int points = scanner.nextInt();
            scanner.nextLine();
            addQuestion(questionType,question,points);
        }
        System.out.println("Congrats, you created the quiz titled "+name);


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
                        answer = splitUp[4];
                        pointValue = Integer.parseInt(splitUp[5]);
                        q= new MultipleChoice(question,answerChoices, choices,answer,pointValue);
                    }
                    quiz.add(q);
                }
                i++;
            }
        }catch (IOException e){
            System.out.println("There was a problem creating Quiz object from filename");
        }

    }

    public Question addQuestion(String type ,String question,int points){
        Question q = null;
        if(type.equalsIgnoreCase("True False")){
            q = new TrueFalse(question,points);
        }else if(type.equalsIgnoreCase("Fill in the blank")){
            q = new FillInTheBlank(question,points);
        }else if(type.equalsIgnoreCase("Multiple Choice")){
            q = new MultipleChoice(question,points);
        }else{
            System.out.println("There was an error in the addQuestion method!");
        }
        return q;
    }

    public static void main(String[] args) {
        new Quiz();

    }
}



