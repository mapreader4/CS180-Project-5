import java.util.Scanner;

public class MultipleChoice extends Question { ;
    int numChoices;
    String[]answerChoices;
    int correctAnswerIndex;


    public MultipleChoice(String question, int numChoices, String[]answerChoices, String answer,int pointValue){
        super(question, pointValue);
        this.numChoices = numChoices;
        this.answerChoices = answerChoices;
        int index = findCorrectAnswer(answer);
        if(index == -1){
            System.out.println("There was an error finding the correct answer in the array!");
        }else{
            this.correctAnswerIndex = index;
        }


    }
    public MultipleChoice(String question, int points){
        super(question,points);
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many answer choices do you want for this question?");
        int numChoices = scanner.nextInt();
        String[]answerChoices = new String[numChoices];
        for(int i =0 ; i <numChoices ; i++){
            System.out.println("Answer choice "+(i+1)+"is:");
            String choice = scanner.nextLine();
            answerChoices[i] = choice;
        }
        System.out.println("What answer choice is correct? (Enter a number)");
        correctAnswerIndex = scanner.nextInt()-1;

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
