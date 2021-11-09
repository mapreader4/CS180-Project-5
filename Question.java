public abstract class Question {
    abstract void displayQuestion();
    abstract String saveString();
    String question;
    int pointValue;
    public Question(String question, int pointValue){
        this.question = question;
        this.pointValue = pointValue;
    }

    public Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public static void main(String[] args) {
        String[]answerChoices = {"yes","no"};
        MultipleChoice thing = new MultipleChoice("Hi?",2,answerChoices,"yes",10);
        MultipleChoice thing2 = new MultipleChoice("Hi!",2,answerChoices,"yes",10);
        Question[]work = new Question[2];
        work[0]=thing;
        work[1]=thing2;
        for(int i = 0 ; i <2; i ++){
            work[i].displayQuestion();
        }
    }
}
