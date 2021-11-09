import java.io.Serializable;
import java.util.Scanner;

public class MultipleChoice extends Question implements Serializable { ;
    int numChoices;
    String[]answerChoices;
    int correctAnswerIndex;


    public MultipleChoice(String question, int numChoices, String[]answerChoices,int answerIndex,int pointValue){
        super(question, pointValue);
        this.numChoices = numChoices;
        this.answerChoices = answerChoices;
        if(answerIndex== -1){
            System.out.println("There was an error finding the correct answer in the array!");
        }else{
            this.correctAnswerIndex = answerIndex;
        }


    }
    public int findCorrectAnswer(String answer){
        for(int i = 0 ; i < numChoices ; i++){
            if(answerChoices[i].equalsIgnoreCase(answer)){
                return i;
            }
        }
        return -1;
    }
    public void displayQuestion(){
        System.out.println(this.question);
        for(int i = 0 ; i <numChoices;i++){
            System.out.println("["+(i+1)+"] "+answerChoices[i]);
        }

    }

    public String saveString(){ //saves the question to a file
        String s;
        s = "multipleChoice|"+question+"|"+numChoices+"|";
        for(int i = 0 ; i < answerChoices.length;i++){
            if(i == answerChoices.length-1){
                s = answerChoices[i];
            }
            s+=answerChoices[i]+",";
        }
        s+="|"+correctAnswerIndex+"|"+pointValue;
        return s;
    }
}
