import java.util.ArrayList;
import java.util.Objects;

/**
 * Teacher Class
 *
 *@author Aditya Menon (add your name if you implemented any functionality)
 *@version Nov 13, 2021
 */

public class Teacher extends User{

    private ArrayList<Course> courses = new ArrayList<>(); //list of courses created by teacher

    public Teacher (String username, String password) {

        super(username, password, true);
    }

//    public void addCourse(int courseNumber, ArrayList<Student> students, ArrayList<Quiz> quizzes) {
//
//        boolean alreadyThere = false; //ensures no duplicate course is added
//        for (Course c : courses) {
//            if (c.getCourseNumber() == courseNumber) {
//                alreadyThere = true;
//            }
//        }
//
//        if (!alreadyThere) {
//            courses.add(new Course(this, courseNumber));
//        }
//    }
    
    public void addCourse(Course c) {
        boolean alreadyThere = false;
        for (Course cs : courses) {
            if (c.equals(cs)) {
                alreadyThere = true;
            }
        }
        
        if (!alreadyThere) {
            courses.add(c);
        }
    }   
                

    public void removeCourse(int courseNumber) {
        for (Course c : courses) {
            if (c.getCourseNumber() == courseNumber) {
                courses.remove(c);
            }
        }
    }

    public void editCourseName(String oldCourse, String newCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseName().equals(oldCourse)) { //ensures course exists before editing
                courses.get(i).setCourseName(newCourse);
            }
        }
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public Course getCourse(int i) {
        for (Course c : courses) {
            if (c.getCourseNumber() == i) {
                return c;
            }
        }    
        return null;     
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + courses +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isTeacher=" + isTeacher +
                '}';
    }
}
