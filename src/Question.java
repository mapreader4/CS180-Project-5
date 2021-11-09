import java.io.Serializable;

public abstract class Question implements Serializable {
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
}
