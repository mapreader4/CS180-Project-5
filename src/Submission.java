import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Submission {
    Student student;
    Quiz quizz;
    int uniqueId;
    public Submission(Student student, Quiz quizz, int uniqueId){
        this.student=student;
        this.quizz=quizz;
        this.uniqueId=uniqueId;
    }
    public Object[][] takingTheQuiz(Scanner scanner) {
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
                        return null;
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
                        return null;
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
                        return null;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            }
        }
        return needToBeSubmitted;
    }

    public void SubmissionReport(){

    }
    public void TeacherGradingSubmission(String relevantInfo){
        // can quiz have a course instance field so that the quiz can be traced back to the teacher through the course
        // can teacher have a method that grades questions when the question Object and actual answer are in an arraylist.
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
