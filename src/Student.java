import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class Student extends User {

    private ArrayList<String> courses;
    private ArrayList<Double> grades;
    private ArrayList<Quiz> quizzesTaken;

    public Student(String username, String password) {
        super(username, password, false);
    }

    public void addToCourse(String course, String courseFilePath) {
        boolean exists = false;
        boolean alreadyThere = false;

        for (String c : courses) {
            if (c.equals(course)) {
                alreadyThere = true;
            }
        }
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(courseFilePath));
            String check = "";
            while ((check = bfr.readLine()) != null) {
                if (check.equals(course)) {
                    exists = true;
                }
            }

            if (!exists && !alreadyThere) {
                courses.add(course);
                System.out.println("Course added!");
            } else {
                System.out.println("Course already being taken by student or does not exist!");
            }
        } catch (IOException io) {
            System.out.println("Error reading file!");
        }

    }

    public void addGradePlusQuiz(Quiz quiz, double grade) {
        quizzesTaken.add(quiz);
        grades.add(grade);
    }

}
