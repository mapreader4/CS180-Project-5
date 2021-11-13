import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Submission {
    Student student;
    Quiz quizz;
    String name;
    public Submission(Student student, Quiz quizz){
        this.student=student;
        this.quizz=quizz;
    }
    public boolean takeQuiz(Scanner scanner) {
        Object[][] needToBeSubmitted = new Object[quizz.getQuiz().size()][2];
        String studentAnswer;
        for (int i = 0; i < quizz.getQuiz().size(); i++) {
            Question temp = quizz.getQuiz().get(i);
            if (quizz.getQuiz().get(i).getClass() == FillInTheBlank.class) {
                FillInTheBlank temp2 = (FillInTheBlank) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    if (option > 1 || option < 0) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        return false;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            } else if (quizz.getQuiz().get(i).getClass() == TrueFalse.class) {
                TrueFalse temp2 = (TrueFalse) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    if (option > 1 || option < 0) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        return false;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            } else if (quizz.getQuiz().get(i).getClass() == MultipleChoice.class) {
                MultipleChoice temp2=(MultipleChoice) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    if (option > 1 || option < 0) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        return false;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            }
        }
        submissionReport(needToBeSubmitted,scanner);
        return true;
    }

    public void submissionReport(Object[][] needToBeSubmitted,Scanner scanner){

        String filepath = createsNewFile();
        try(PrintWriter pw =new PrintWriter(new FileWriter(filepath))){
            pw.println(student.getUsername()+": "+ quizz.getName());
            for(int i=0;i<needToBeSubmitted.length;i++){
                if(needToBeSubmitted[i][0].getClass()==FillInTheBlank.class){
                    FillInTheBlank temp = (FillInTheBlank) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.getAnswer());
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.getAnswer().equals(needToBeSubmitted[i][1])) {
                    pw.println("Points got: " + temp.getPointValue());
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
                else if(needToBeSubmitted[i][0].getClass()==TrueFalse.class){
                    TrueFalse temp=(TrueFalse) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.getAnswer());
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.getAnswer().equals(needToBeSubmitted[i][1])) {
                        pw.println("Points got: " + temp.getPointValue());
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
                else if(needToBeSubmitted[i][0].getClass()==MultipleChoice.class){
                    MultipleChoice temp=(MultipleChoice) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.correctAnswerIndex);
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.correctAnswerIndex==Integer.parseInt((String)needToBeSubmitted[i][1])){
                        pw.println("Points got: " + temp.getPointValue());
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
            return;
        }
        student.addSubmission(this);
    }
    public String createsNewFile(){
        Random a=new Random();
        String filepath="Submission"+a.nextInt(10000);
        this.name=filepath;
        return filepath;
    }
    public String readingTheAnswerFromFile(Scanner scanner) {
        String studentAnswer = "";
        try {
            System.out.println("Enter the file path:");
            String filepath = scanner.nextLine();
            File f = new File(filepath);
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                studentAnswer += line;
                line = bfr.readLine();
            }
        } catch (IOException e) {
            System.out.println("There was an error accessing your file.");
            return null;
        }
        return studentAnswer;
    }

}
