import java.io.Serializable;

public class MultipleChoice extends Question {
    int numChoices;
    String[]answerChoices;
    int correctAnswerIndex;


    public MultipleChoice(String question, int numChoices, String[]answerChoices,int answerIndex,int pointValue){
        super(question, pointValue);
        this.numChoices = numChoices;
        this.answerChoices = answerChoices;
        this.correctAnswerIndex=answerIndex;
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
        System.out.println(getQuestion());
        for(int i = 0 ; i <numChoices;i++){
            System.out.println("["+(i+1)+"] "+answerChoices[i]);
        }

    }

    public String saveString(){ //saves the question to a file
        String s;
        s = "multipleChoice|"+getQuestion()+"|"+numChoices+"|";
        for(int i = 0 ; i < answerChoices.length;i++){
            if(i == answerChoices.length-1){
                s+= answerChoices[i];
                break;
            }
            s+=answerChoices[i]+",";
        }
        s+="|"+correctAnswerIndex+"|"+getPointValue();
        return s;
    }
}

