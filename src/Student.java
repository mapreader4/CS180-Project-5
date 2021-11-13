import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Student Class
 *
 *@author Aditya Menon (add your name if you implemented any functionality)
 *@version Nov 13, 2021
 */

public class Student extends User {

    private ArrayList<String> courses;
    private ArrayList<Double> grades;
    private ArrayList<Quiz> quizzesTaken;
    private ArrayList<Quiz> quizzesTakenWithScores;
    private ArrayList<Submission> submissions;

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

            bfr.close();

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
        quizzesTakenWithScores.add(quiz);
        grades.add(grade);
    }
    
    public String showTotalScores() {
        String s = "";
        if (quizzesTakenWithScores.size() > 0) {
            for (int i = 0; i < quizzesTakenWithScores.size(); i++) {
                s += quizzes.get(i).getName + ": " + grades.get(i) + "\n";
            }
            return s;
        } else {
            return "No scores assigned!";
        }
    }
    
    public String showQuizzesTaken() {
        String s = "";
        if (quizzesTaken.size() > 0) {
            for (int i = 0; i < quizzesTaken.size(); i++) {
                s += quizzesTaken.get(i).getName + "\n";
            }
            return s;
        } else {
            return "No quizzes taken!";
        }    
    }
    
    public void addQuizTaken(Quiz quiz) {
        quizzesTaken.add(quiz);
    }
    
    public String showStringCourses() {
        String s = "Courses: \n";
        for (int i = 0; i < courses.size(); i++) {
            s += courses.get(i).getName() + "\n";
        }
        
        return s;
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }    
    
    public Course getCourse(int i) {
        return courses.get(i);
    }
    
    public void addSubmission(Submssion s) {
        submissions.add(s);
    }    
}
